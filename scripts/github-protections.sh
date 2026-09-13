#!/usr/bin/env bash
# shellcheck shell=bash
#
# github-protections.sh -- apply GitHub-side repository protections.
#
# This script configures the repository on GitHub (branch ruleset, tag
# ruleset, Actions hardening, Dependabot, secret scanning, private
# vulnerability reporting). It is idempotent: run it again after changing
# the values below to update the existing rulesets.
#
# Usage:
#   GH_TOKEN=<token> ./scripts/github-protections.sh --dry-run
#   GH_TOKEN=<token> ./scripts/github-protections.sh
#
# Token requirements (either one):
#   * classic PAT with the `repo` scope, or
#   * fine-grained PAT with a single repository permission:
#       Administration: Read and write
#     (Metadata: Read is added automatically; every setting this script
#     touches is admin-level.)
#
# Environment overrides:
#   BRANCH_NAME                  default: main
#   REQUIRED_APPROVALS           default: 0  (raise to 1+ when more maintainers join)
#   REQUIRE_CODE_OWNER_REVIEW    default: 0  (set to 1 to require CODEOWNERS review;
#                                             do not enable while the only owner is
#                                             the person opening the PRs)
#   REQUIRE_SIGNED_COMMITS       default: 0  (set to 1 only if every committer signs)
#   REQUIRED_CHECKS              comma-separated override of the required status checks
#   REPO_OWNER / REPO_NAME       default: parsed from the origin remote

set -euo pipefail

# ---------------------------------------------------------------------------
# Configuration
# ---------------------------------------------------------------------------
BRANCH_NAME="${BRANCH_NAME:-main}"
REQUIRED_APPROVALS="${REQUIRED_APPROVALS:-0}"
REQUIRE_CODE_OWNER_REVIEW="${REQUIRE_CODE_OWNER_REVIEW:-0}"
REQUIRE_SIGNED_COMMITS="${REQUIRE_SIGNED_COMMITS:-0}"

# Status checks enforced on every pull request. These must match the job
# `name:` values of workflows that run on `pull_request`. Path-filtered
# workflows (e.g. "Gradle wrapper validation") and push/schedule-only
# workflows are intentionally NOT required, otherwise PRs would hang forever.
REQUIRED_CHECKS_DEFAULT=(
  "Demo APK"
  "CI actions tests"
  "CodeQL"
  "Composables stability"
  "Dependency review"
  "Integration tests"
  "Lint"
  "PR description"
  "Unit tests"
)

if [[ -n "${REQUIRED_CHECKS:-}" ]]; then
  IFS=',' read -r -a REQUIRED_CHECKS_LIST <<<"$REQUIRED_CHECKS"
else
  REQUIRED_CHECKS_LIST=("${REQUIRED_CHECKS_DEFAULT[@]}")
fi

MAIN_RULESET_NAME="main-branch-protection"
TAG_RULESET_NAME="release-tag-protection"
TAG_PATTERN="refs/tags/v*"

DRY_RUN=0
case "${1:-}" in
  --dry-run) DRY_RUN=1 ;;
  -h|--help)
    sed -n '2,30p' "$0" | sed 's/^# \{0,1\}//'
    exit 0
    ;;
  "") ;;
  *) echo "error: unknown argument: $1" >&2; exit 2 ;;
esac

TOKEN="${GH_TOKEN:-${GITHUB_TOKEN:-}}"
[[ -n "$TOKEN" ]] || {
  echo "error: no token found. Set GH_TOKEN (or GITHUB_TOKEN) first." >&2
  echo "       See the header of this script for the required token scopes." >&2
  exit 1
}

for cmd in curl python3; do
  command -v "$cmd" >/dev/null 2>&1 || { echo "error: '$cmd' is required" >&2; exit 1; }
done

API="https://api.github.com"
ACCEPT="Accept: application/vnd.github+json"
API_VERSION="X-GitHub-Api-Version: 2022-11-28"
AUTH="Authorization: Bearer $TOKEN"

log() { printf '%s\n' "$*"; }
warn() { printf 'warning: %s\n' "$*" >&2; }
die() { printf 'error: %s\n' "$*" >&2; exit 1; }

# ---------------------------------------------------------------------------
# Repository detection and permission check
# ---------------------------------------------------------------------------
REMOTE_URL="$(git remote get-url origin 2>/dev/null || true)"
[[ -n "$REMOTE_URL" ]] || die "no 'origin' remote found; run this from the repository root"
SLUG="$(printf '%s' "$REMOTE_URL" |
  sed -E 's#^git@github\.com:##; s#^ssh://git@github\.com/##; s#^https?://github\.com/##; s#\.git$##')"
OWNER="${REPO_OWNER:-${SLUG%%/*}}"
REPO="${REPO_NAME:-${SLUG##*/}}"
[[ -n "$OWNER" && -n "$REPO" && "$OWNER" != "$REPO" ]] ||
  die "could not detect owner/repo from remote '$REMOTE_URL' (use REPO_OWNER/REPO_NAME)"

log "Repository : $OWNER/$REPO"
log "Branch     : $BRANCH_NAME"
log "Dry run    : $DRY_RUN"
log ""

REPO_JSON="$(curl -sS -H "$AUTH" -H "$ACCEPT" -H "$API_VERSION" "$API/repos/$OWNER/$REPO")"
IS_ADMIN="$(printf '%s' "$REPO_JSON" | python3 -c \
  'import json,sys; print(str(json.load(sys.stdin).get("permissions",{}).get("admin", False)).lower())')"
OWNER_ID="$(printf '%s' "$REPO_JSON" | python3 -c \
  'import json,sys; print(json.load(sys.stdin)["owner"]["id"])')"
[[ "$IS_ADMIN" == "true" ]] ||
  die "the token does not have admin rights on $OWNER/$REPO (branch protection requires repo admin)"
log "Token has admin rights (owner id: $OWNER_ID)."

# ---------------------------------------------------------------------------
# HTTP helpers
# ---------------------------------------------------------------------------
FAILURES=0

# _call <hard|optional> <METHOD> <path> [json-body]
_call() {
  local mode="$1" method="$2" path="$3" data="${4:-}" tmp code
  if ((DRY_RUN)); then
    log "  DRY-RUN $method $path"
    [[ -n "$data" ]] && log "          $data"
    return 0
  fi
  tmp="$(mktemp)"
  if [[ -n "$data" ]]; then
    code="$(curl -sS -o "$tmp" -w '%{http_code}' -X "$method" \
      -H "$AUTH" -H "$ACCEPT" -H "$API_VERSION" -H "Content-Type: application/json" \
      --data "$data" "$API$path")" || code="000"
  else
    code="$(curl -sS -o "$tmp" -w '%{http_code}' -X "$method" \
      -H "$AUTH" -H "$ACCEPT" -H "$API_VERSION" "$API$path")" || code="000"
  fi
  case "$code" in
    2*) log "  ok    $method $path ($code)" ;;
    404)
      if [[ "$mode" == "optional" ]]; then
        warn "$method $path -> 404 (feature not available for this repo/plan)"
      else
        printf '  FAIL  %s %s (%s)\n' "$method" "$path" "$code" >&2
        cat "$tmp" >&2; printf '\n' >&2
        FAILURES=$((FAILURES + 1))
      fi
      ;;
    409|422)
      if [[ "$mode" == "optional" ]]; then
        warn "$method $path -> $code (setting does not apply); continuing"
      else
        printf '  FAIL  %s %s (%s)\n' "$method" "$path" "$code" >&2
        cat "$tmp" >&2; printf '\n' >&2
        FAILURES=$((FAILURES + 1))
      fi
      ;;
    *)
      printf '  FAIL  %s %s (%s)\n' "$method" "$path" "$code" >&2
      cat "$tmp" >&2; printf '\n' >&2
      FAILURES=$((FAILURES + 1))
      ;;
  esac
  rm -f "$tmp"
}

call() { _call hard "$@"; }
call_optional() { _call optional "$@"; }

ruleset_id_for() { # <name>
  curl -sS -H "$AUTH" -H "$ACCEPT" -H "$API_VERSION" \
    "$API/repos/$OWNER/$REPO/rulesets?per_page=100" |
    python3 -c 'import json,sys
name = sys.argv[1]
for r in json.load(sys.stdin):
    if r.get("name") == name:
        print(r["id"])
        break' "$1"
}

upsert_ruleset() { # <name> <json-payload>
  local name="$1" payload="$2" id
  if ((DRY_RUN)); then
    log "  DRY-RUN upsert ruleset '$name'"
    log "          $payload"
    return 0
  fi
  id="$(ruleset_id_for "$name")"
  if [[ -n "$id" ]]; then
    call PUT "/repos/$OWNER/$REPO/rulesets/$id" "$payload"
  else
    call POST "/repos/$OWNER/$REPO/rulesets" "$payload"
  fi
}

build_ruleset() { # <main|tag>
  python3 - "$1" "$OWNER_ID" "$REQUIRED_APPROVALS" "$REQUIRE_CODE_OWNER_REVIEW" \
    "$REQUIRE_SIGNED_COMMITS" "$BRANCH_NAME" "$MAIN_RULESET_NAME" "$TAG_RULESET_NAME" \
    "$TAG_PATTERN" "${REQUIRED_CHECKS_LIST[@]}" <<'PY'
import json
import sys

(target, owner_id, approvals, code_owner, signatures, branch,
 main_name, tag_name, tag_pattern, *checks) = sys.argv[1:]
owner_id = int(owner_id)
approvals = int(approvals)
code_owner = code_owner == "1"
signatures = signatures == "1"

if target == "main":
    rules = [
        {"type": "deletion"},
        {"type": "non_fast_forward"},
        {"type": "required_linear_history"},
        {
            "type": "pull_request",
            "parameters": {
                "required_approving_review_count": approvals,
                "dismiss_stale_reviews_on_push": True,
                "require_code_owner_review": code_owner,
                "require_last_push_approval": approvals > 0,
                "required_review_thread_resolution": True,
            },
        },
        {
            "type": "required_status_checks",
            "parameters": {
                "strict_required_status_checks_policy": True,
                "do_not_enforce_on_create": False,
                "required_status_checks": [{"context": c} for c in checks],
            },
        },
    ]
    if signatures:
        rules.append({"type": "required_signatures"})
    payload = {
        "name": main_name,
        "target": "branch",
        "enforcement": "active",
        # The repository owner may bypass only when merging a pull request
        # (e.g. to recover from a failing/flaky required check). Direct pushes
        # and force-pushes stay blocked for everyone, including admins.
        "bypass_actors": [
            {"actor_id": owner_id, "actor_type": "User", "bypass_mode": "pull_request"}
        ],
        "conditions": {"ref_name": {"include": [f"refs/heads/{branch}"], "exclude": []}},
        "rules": rules,
    }
else:
    payload = {
        "name": tag_name,
        "target": "tag",
        "enforcement": "active",
        # Only the owner may rewrite/delete a release tag, and only by
        # bypassing the rule on purpose (e.g. to fix a bad release).
        "bypass_actors": [
            {"actor_id": owner_id, "actor_type": "User", "bypass_mode": "always"}
        ],
        "conditions": {"ref_name": {"include": [tag_pattern], "exclude": []}},
        "rules": [{"type": "deletion"}, {"type": "update"}],
    }

print(json.dumps(payload))
PY
}

# ---------------------------------------------------------------------------
# 1. Repository settings, secret scanning, Dependabot
# ---------------------------------------------------------------------------
log "== Repository security settings =="
call_optional PATCH "/repos/$OWNER/$REPO" '{
  "delete_branch_on_merge": true,
  "security_and_analysis": {
    "secret_scanning": {"status": "enabled"},
    "secret_scanning_push_protection": {"status": "enabled"}
  }
}'
call_optional PUT "/repos/$OWNER/$REPO/vulnerability-alerts"
call_optional PUT "/repos/$OWNER/$REPO/automated-security-fixes"
call_optional PUT "/repos/$OWNER/$REPO/private-vulnerability-reporting"

# ---------------------------------------------------------------------------
# 2. Actions hardening
# ---------------------------------------------------------------------------
log ""
log "== GitHub Actions settings =="
# Actions are pinned to full commit SHAs in this repo; enforce it.
call PUT "/repos/$OWNER/$REPO/actions/permissions" \
  '{"enabled": true, "allowed_actions": "all", "sha_pinning_required": true}'
# Workflow tokens are read-only unless a workflow opts in, and Actions may
# not create or approve pull requests.
call PUT "/repos/$OWNER/$REPO/actions/permissions/workflow" \
  '{"default_workflow_permissions": "read", "can_approve_pull_request_reviews": false}'
# Fork pull requests from outside contributors need maintainer approval.
call_optional PUT "/repos/$OWNER/$REPO/actions/permissions/fork-pr-contributor-approval" \
  '{"approval_policy": "all_external_contributors"}'

# ---------------------------------------------------------------------------
# 3. Rulesets: main branch and release tags
# ---------------------------------------------------------------------------
log ""
log "== Branch ruleset ($BRANCH_NAME) =="
upsert_ruleset "$MAIN_RULESET_NAME" "$(build_ruleset main)"

log ""
log "== Tag ruleset ($TAG_PATTERN) =="
upsert_ruleset "$TAG_RULESET_NAME" "$(build_ruleset tag)"

# ---------------------------------------------------------------------------
# Summary
# ---------------------------------------------------------------------------
log ""
if ((FAILURES > 0)); then
  log "Finished with $FAILURES failure(s). Review the output above."
  exit 1
fi
log "Done. All protections are applied."
log "Verify at: https://github.com/$OWNER/$REPO/settings/rules"
