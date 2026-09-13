# Troubleshooting CI failures

If you see any of the PR checks failing (❌) go to [Actions](https://github.com/Pranesh-Selvaraj/Ratio/actions) and find it there. Or simply click "Details" next to the failed check and explore the logs to see why it has failed.

## PR description check

It means that you didn't follow our [official PR template](../.github/PULL_REQUEST_TEMPLATE.md).
Update your PR description with all necessary information. You can also check the exact error by
clicking "Details" on the failing (❌) check.

## Lint

We use the [standard Android Lint](https://developer.android.com/studio/write/lint) plus [Slack's compose-lints](https://slackhq.github.io/compose-lints/) as an addition to enforce proper Compose usage.

**To run Lint locally:**
```
./scripts/lint.sh
```

If the Lint errors are caused by a legacy code, you can suppress them using a baseline.

**Suppress Lint** (only if you're sure that Lint is wrong)

Add `@Suppress("ID_OF_CHECK")` to the code.

**Lint baseline** (not recommended)
```
./scripts/lintBaseline.sh
```

## Unit tests

If this job is failing this means that your changes break an existing unit test. You must identify the failing tests and fix your code.

**To run the Unit tests locally:**
```
./gradlew testDebugUnitTest
```

## Compose Stability

This GitHub Action checks whether your `@Composable` functions are stable (i.e. "restartable" and "skippable"). If it fails it means that some of your composables are unstable. That causes unnecessary recompositions which can lead to lost frames and laggy UI/UX especially when animation or scrolling. You must fix that! To fix it, open the failing working and see the output from the report - it tells you which `@Composable` functions are unstable and what parameters cause that.

**Fixing Stability issues:**
1. Read https://developer.android.com/jetpack/compose/performance/stability/fix
2. https://developer.android.com/jetpack/compose/performance/stability

**Compose Stability baseline** (not recommended)
```
./scripts/composeStabilityBaseline.sh
```
Do that only if the failure is in legacy code. If the script is failing, open it and execute the commands inside it manually.
