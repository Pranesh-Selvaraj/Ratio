# Repository Protection

This document describes the protections applied to this repository and how to
re-apply or change them.

## Apply / update the protections

The GitHub-side settings cannot be stored as files, so they are applied with a
script. Run it from the repository root:

```bash
# 1. Preview the API calls (no changes are made)
GH_TOKEN=<token> ./scripts/github-protections.sh --dry-run

# 2. Apply
GH_TOKEN=<token> ./scripts/github-protections.sh
```

Token requirements (either one):

- classic PAT with scopes `repo` and `workflow`, or
- fine-grained PAT with **Administration: Read and write**, **Actions: Read and
  write**, **Contents: Read and write**.

The script is idempotent and stores the rulesets under the names
`main-branch-protection` and `release-tag-protection`. Verify the result at
<https://github.com/Pranesh-Selvaraj/Ratio/settings/rules>.

## What is enforced

### `main` branch ruleset (`main-branch-protection`)

| Rule | Effect |
| --- | --- |
| Pull request required | No direct pushes to `main`; every change goes through a PR. |
| Approvals | 0 by default (solo maintainer). Raise `REQUIRED_APPROVALS=1` once other maintainers join. |
| Conversation resolution | All review threads must be resolved before merge. |
| Dismiss stale reviews | Approvals are dismissed when new commits are pushed. |
| Required status checks | 11 CI checks must pass, and the branch must be up to date with `main`. |
| Linear history | Squash or rebase merges only; no merge commits. |
| Deletion / force push | Blocked for everyone. |
| Bypass | Only the repository admin, and only while merging a pull request (break-glass for a flaky required check). Direct pushes remain blocked for admins too. |

Required status checks:

`Demo APK`, `CI actions tests`, `CodeQL`, `Composables stability`,
`Dependency review`, `Detekt`, `Integration tests`, `Lint`,
`Paparazzi screenshots`, `PR description`, `Unit tests`.

`Gradle wrapper validation` is intentionally **not** required because it is
path-filtered (it only runs when wrapper files change, so requiring it would
leave unrelated PRs stuck). Update the list in
`scripts/github-protections.sh` if a workflow's `name:` changes, or run with
`REQUIRED_CHECKS="Check A,Check B"`.

### Release tag ruleset (`release-tag-protection`)

Tags matching `v*` cannot be deleted or moved. New tags can still be created by
the release workflow, which is how `release.yml` publishes versions.
Only the admin can deliberately bypass the rule.

### GitHub Actions hardening

- Default workflow token is **read-only**; workflows that need more must opt in
  with an explicit `permissions:` block (all workflows here do).
- Actions may **not** create or approve pull requests.
- All third-party actions must be **pinned to a full commit SHA**
  (`sha_pinning_required: true`). All `uses:` in `.github/workflows/` are
  pinned and annotated with the version comment so Dependabot can update them.
- Fork pull request workflows require approval from a maintainer before running.

### Repository security

- Dependabot alerts and automated security fixes are enabled.
- Secret scanning and push protection are enabled (public repo).
- Private vulnerability reporting is enabled (see `SECURITY.md`).
- Branches are automatically deleted after merge.

### In-repo protections

- `.github/CODEOWNERS` assigns everything to `@Pranesh-Selvaraj`.
- `SECURITY.md` documents the vulnerability reporting process.
- CodeQL (`codeql.yml`) runs code scanning on pushes, PRs and weekly.
- Dependency review (`dependency-review.yml`) fails PRs that add high-severity
  vulnerable dependencies.
- OpenSSF Scorecard (`scorecard.yml`) audits the repo weekly.
- `.gitignore` blocks accidental commits of release signing material
  (`sign.jks`, `sign.jks.b64`, `google-play-console-user.json`,
  `play_config.json.b64`).

## Automation / bots operating on this repo

| Bot | Trigger | What it does |
| --- | --- | --- |
| **dependabot[bot]** (`.github/dependabot.yml`) | Weekly | Opens grouped PRs bumping Gradle dependencies (`dependabot/gradle/*`) and GitHub Actions versions (`dependabot/github_actions/*`). Actions are updated to new commit SHAs. |
| **github-actions[bot] — Version bump** (`version_bump.yml`) | Sundays 00:00 UTC + manual | Proposes a date-based `version-name` and incremented `version-code` in `gradle/libs.versions.toml` via a PR on a `version-bump-*` branch labeled `auto-update`. Merging it is a human decision; it releases nothing by itself. |
| **github-actions[bot] — Gradle Wrapper Upgrade** (`upgrade-gradle-wrapper.yml`) | Daily 08:00 UTC + manual | Runs `./gradlew upgradeGradleWrapperRatio` and commits/opens a PR to keep the Gradle wrapper current. |
| **actions/stale** (`stale.yml`) | Daily 08:00 UTC + manual | Marks issues stale after 30 days (closed after 7 more) and PRs stale after 2 days (closed after 1 more). Issues/PRs labeled `keep`, `P0` or `bug` are exempt. |
| **Issue created automation** (`issue_created.yml` → `ci-actions/issue-create-comment`) | New issue | Posts a thank-you comment with the contribution guidelines and tags the admin for review/approval. |
| **Issue assign automation** (`issue_assign.yml` → `ci-actions/issue-assign`) | New issue comment | Parses the comment for a "take this issue" intention and auto-assigns the commenter, but only if the issue has the approved label and is not already assigned. Otherwise replies that it is already taken or not approved. |
| **CodeQL** (`codeql.yml`) | Push to `main`, PR, weekly | Static security/quality analysis of the Kotlin code; results go to GitHub code scanning. |
| **OpenSSF Scorecard** (`scorecard.yml`) | Push to `main`, weekly, branch protection changes | Audits supply-chain and repo security practices; results are published and uploaded as SARIF. |
| **Release** (`release.yml`) | Manual (`workflow_dispatch`) | Builds the FOSS APK from source (`assembleDemo`) and attaches it to a GitHub Release tagged `v<version>-<code>`. No Google Play (or any store) publishing and no signing secrets required. |

## Maintenance notes

- **More maintainers join:** set `REQUIRED_APPROVALS=1` and, if desired,
  `REQUIRE_CODE_OWNER_REVIEW=1`, then re-run the script. Do not enable required
  approvals/code-owner review while the only maintainer is also the PR author —
  GitHub does not let you approve your own PR and you would lock yourself out.
- **Signed commits:** set `REQUIRE_SIGNED_COMMITS=1` only after every committer
  (including local development) signs their commits.
- **Proper release signing (optional):** GitHub Releases currently ship a
  debug-signed FOSS build so no secrets are needed. If you ever want a
  keystore-signed APK, add the `SIGNING_*` repository secrets, switch the build
  step in `release.yml` to `./gradlew assembleRelease`, and (if desired) move
  the secrets into an environment with required reviewers.
- **Rulesets vs classic branch protection:** this repo uses rulesets. Classic
  branch protection is left untouched; do not enable both for the same branch
  to avoid confusing, conflicting requirements.
