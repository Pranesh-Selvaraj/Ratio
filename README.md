> [!IMPORTANT]
> **Ratio is an independent fork of [Ivy Wallet](https://github.com/Ivy-Apps/ivy-wallet).**
>
> The original Ivy Wallet project is no longer maintained by its creators. Ratio continues development under the [GPL-3.0 License](LICENSE), keeping the same license and crediting the original authors.
>
> - **Upstream**: [Ivy-Apps/ivy-wallet](https://github.com/Ivy-Apps/ivy-wallet)
> - **License**: [GNU GPL-3.0](LICENSE)

[![Latest Release](https://img.shields.io/github/v/release/Pranesh-Selvaraj/Ratio)](https://github.com/Pranesh-Selvaraj/Ratio/releases)
[![APK](https://github.com/Pranesh-Selvaraj/Ratio/actions/workflows/apk.yml/badge.svg)](https://github.com/Pranesh-Selvaraj/Ratio/actions/workflows/apk.yml)
[![Issues](https://img.shields.io/github/issues/Pranesh-Selvaraj/Ratio)](https://github.com/Pranesh-Selvaraj/Ratio/issues)

[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![GitHub Repo stars](https://img.shields.io/github/stars/Pranesh-Selvaraj/Ratio?style=social)](https://github.com/Pranesh-Selvaraj/Ratio/stargazers)
[![Fork Ratio](https://img.shields.io/github/forks/Pranesh-Selvaraj/Ratio?logo=github&style=social)](https://github.com/Pranesh-Selvaraj/Ratio/fork)

# Ratio: money manager

Ratio is a free and open source **money management Android app**. It's written using **100% Kotlin and Jetpack Compose**. It's designed to help you keep track of your personal finances with ease.

Think of Ratio as a manual expense tracker that tries to replace the good old spreadsheet for managing your finances.

**Do you know? Ask yourself.**

1) How much money do I have in total?

2) How much did I spend this month and what did I spend it on?

3) How much can I spend and still meet my financial goals?

A money management app can help you answer these questions.

> To support this free open source project, please give it a star. ⭐
> This means a lot to us. Thank you so much! [![GitHub Repo stars](https://img.shields.io/github/stars/Pranesh-Selvaraj/Ratio?style=social)](https://github.com/Pranesh-Selvaraj/Ratio/stargazers)

## Project Requirements

- Java 17+
- The **latest stable** Android Studio (for easy install use [JetBrains Toolbox](https://www.jetbrains.com/toolbox-app/))

### Initialize the project

**1. Clone the repo**

Instructions in [CONTRIBUTING.md](./CONTRIBUTING.md).

### Need help?

Open an [issue](https://github.com/Pranesh-Selvaraj/Ratio/issues) on GitHub.

## Learning Materials

Ratio is a great place to code and learn. That's why we also link to great learning materials (books, articles, videos), check them out in **[docs/resources 📚](docs/resources/)**.

Make sure to check out our short **[Developer Guidelines 🏗️](docs/Guidelines.md)** to learn more about the technical side of Ratio.

## Tech Stack

### Core

- 100% [Kotlin](https://kotlinlang.org/)
- 100% [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Material3 design](https://m3.material.io/) (UI components)
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) (structured concurrency)
- [Kotlin Flow](https://kotlinlang.org/docs/flow.html) (reactive data stream)
- [Hilt](https://dagger.dev/hilt/) (DI)
- [ArrowKt](https://arrow-kt.io/) (functional programming)


### Testing
- [JUnit4](https://github.com/junit-team/junit4) (test framework, compatible with Android)
- [Kotest](https://kotest.io/) (unit test assertions)
- [Paparazzi](https://github.com/cashapp/paparazzi) (screenshot testing)

### Local Persistence
- [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) (key-value storage)
- [Room DB](https://developer.android.com/training/data-storage/room) (SQLite ORM)

### Networking
- [Ktor client](https://ktor.io/docs/getting-started-ktor-client.html) (HTTP client)
- [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization) (JSON serialization)

### Build & CI
- [Gradle KTS](https://docs.gradle.org/current/userguide/kotlin_dsl.html) (Kotlin DSL)
- [Gradle convention plugins](https://docs.gradle.org/current/samples/sample_convention_plugins.html) (build logic)
- [Gradle version catalogs](https://developer.android.com/build/migrate-to-catalogs) (dependencies versions)
- [GitHub Actions](https://github.com/Pranesh-Selvaraj/Ratio/actions) (CI/CD)
- [Fastlane](https://fastlane.tools/) (uploads the app to the Google Play Store)

### Other
- [Timber](https://github.com/JakeWharton/timber) (logging)
- [Detekt](https://github.com/detekt/detekt) (linter)
- [Ktlint](https://github.com/pinterest/ktlint) (linter)
- [Slack's compose-lints](https://slackhq.github.io/compose-lints/) (linter)

## Contribute

**Want to contribute?** See **[CONTRIBUTING.md](/CONTRIBUTING.md)** [![Fork Ratio](https://img.shields.io/github/forks/Pranesh-Selvaraj/Ratio?logo=github&style=social)](https://github.com/Pranesh-Selvaraj/Ratio/fork)

### Contributors Wall:

<a href="https://github.com/Pranesh-Selvaraj/Ratio/graphs/contributors">
  <img alt="contributors graph" src="https://contrib.rocks/image?repo=Pranesh-Selvaraj/Ratio" />
</a>
<br>
<br>

_Note: It may take up to 24 hours for the [contrib.rocks](https://contrib.rocks/preview?repo=Pranesh-Selvaraj%2FRatio) plugin to update._ 

**P.S.** You'll also be recognized in a special "Contributors" section. We salute you! 👏

## Attribution & License

Ratio is a fork of [Ivy Wallet](https://github.com/Ivy-Apps/ivy-wallet) by [Ivy Apps](https://github.com/Ivy-Apps), originally licensed under the **GNU General Public License v3.0**. The original source code, design and documentation were created by the Ivy Wallet authors and contributors, and their work remains credited here.

In accordance with the GPL-3.0:

- Ratio is distributed under the same [GPL-3.0 License](LICENSE).
- The original copyright notices and license are preserved.
- Source code for Ratio is available in this repository.

Thanks to the Ivy Wallet team for building such a great foundation. 💚

### Original Ivy Wallet Creative Contributors

Folks that helped Ivy Wallet in non-dev, creative ways that can't be captured on GitHub.

<div style="text-align: center">
    <img src="https://avatars.githubusercontent.com/u/62771583?v=4" width="100px;" alt="Stefan Ilijev - Designer"/><br>
    <strong>Stefan Ilijev</strong><br>
    <small>Co-founder and designer of Ivy Wallet. Created the <a href="https://www.figma.com/file/kSwIa07jcHEHZXo6rzx7dn/Design-System?node-id=0%3A1&mode=dev">Ivy design system</a>.</small>
    <br/>
    <br/>
</div>

<div style="text-align: center">
    <img src="https://avatars.githubusercontent.com/u/86833171?v=4" width="100px;" alt="Aditya [ADX]"/><br>
    <strong><a href="https://github.com/adx69" >Aditya</a> </strong><br>
    <br/>
</div>

<div style="text-align: center">
    <img src="https://avatars.githubusercontent.com/u/130169485?v=4" width="100px;" alt="Shymom [SSI]"/><br>
    <strong><a href="https://github.com/SHYMOM" >Shymom</a> </strong><br>
    <br/>
</div>

## Disclaimer

This software is provided on an **"as-is" basis**, without warranties or conditions of any kind. The original Ivy Wallet maintainers are not affiliated with Ratio and are not responsible for it. See [LICENSE](LICENSE) for details.
