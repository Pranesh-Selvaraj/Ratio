# Security Policy

## Supported versions

Only the latest release on the [`main`](https://github.com/Pranesh-Selvaraj/Ratio) branch is supported with security fixes.

## Reporting a vulnerability

Please **do not open a public issue** for security problems.

Report privately via GitHub's [private vulnerability reporting](https://github.com/Pranesh-Selvaraj/Ratio/security/advisories/new) or email the maintainer at [praneshs281@gmail.com](mailto:praneshs281@gmail.com).

Include as much as possible:

- Description of the issue and its impact
- Steps to reproduce or a proof of concept
- Affected version/commit
- Any suggested fix

You can expect an initial response within 7 days. Please give us reasonable time to release a fix before public disclosure.

## Scope

In scope:

- The Android application code and its Gradle modules
- Build, CI/CD and release configuration in this repository
- Data handling (imports/exports, local database, backups)

Out of scope:

- Vulnerabilities in third-party dependencies (please report them upstream)
- Issues that require a rooted device, physical access, or a compromised OS
- Social engineering

## Security practices in this repository

- **FOSS release builds.** Releases are built from source in GitHub Actions and
  signed with the project's public debug keystore — standard practice for open
  source Android apps. They are distributed only via GitHub Releases and are
  never uploaded to Google Play or any other app store.
- **Least-privilege CI.** Workflows run with minimal `GITHUB_TOKEN` permissions; third-party actions are pinned to full commit SHAs (enforced by a repository ruleset) and reviewed.
- **Code scanning.** CodeQL runs on every push/PR and weekly; results are published under the repository's Security tab.
- **Supply chain.** Dependabot version updates and security alerts are enabled; dependency review runs on pull requests; the Gradle wrapper is validated on changes.
- **Secret scanning.** GitHub secret scanning and push protection are enabled for this repository.
- **No cleartext traffic.** The app blocks cleartext (HTTP) network traffic via a network security config.
- **Local-first data.** Ratio stores data on-device. Ads/trackers are not included.

### Debug keystore

`debug.jks` and its password are intentionally public (standard practice for Android debug builds). It is used only for the `debug` and `demo` build types and can never sign a production release.
