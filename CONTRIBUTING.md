# Contributing to Ratio

> [!NOTE]
> Ratio is an independent fork of [Ivy Wallet](https://github.com/Ivy-Apps/ivy-wallet), licensed under [GPL-3.0](LICENSE). Contributions are welcome!


## 1. Fork the repo
Fork of the official Ratio repo by clicking on the badge: [![Fork Ratio](https://img.shields.io/github/forks/Pranesh-Selvaraj/Ratio?logo=github&style=social)](https://github.com/Pranesh-Selvaraj/Ratio/fork).

**[Forking - GitHub tutorial](https://docs.github.com/en/get-started/quickstart/fork-a-repo)**


## 2. Pick an issue
### Workflow:
1. Browse **[Ratio Issues](https://github.com/Pranesh-Selvaraj/Ratio/issues)**.
2. Choose an issue that you understand and like.
> Didn't find anything fitting? Try **[creating a new issue](https://github.com/Pranesh-Selvaraj/Ratio/issues/new/choose)**.
3. Comment on the issue to let the maintainer know you're taking it; it will be assigned manually.

### Contributing rules:
1. Do **not** work on already assigned issues. Ask the assignee first. If more than 7 days have passed, comment and tag [@Pranesh-Selvaraj](https://github.com/Pranesh-Selvaraj) to unassign it.
2. Fix your issue quickly or unassign yourself if you're unable to, in order to not block other contributors.
3. You can only work on one issue at a time.


## 3. Create a feature branch in your fork
Open your forked `Ratio` folder in the terminal and create your issue's branch:
```
git checkout -b fix-issue-{YOUR_ISSUE_NUMBER}
```
> Replace {YOUR_ISSUE_NUMBER} with the ID/number of your issue.


## 4. Time to work
### ⚠️ Very important - read the [Developer Guidelines 🏗️](docs/Guidelines.md) before you begin.

### Workflow:
- Make commits.
- Refactor your code.
- Verify that your implementation works.
- Build often and test that you haven't broken existing features.

### Tips:
- Make sure that you don't break anything with your changes.
- Keep it simple.
- "Don't walk away from complexity, run!"

### Ask Yourself:
- Is that the simplest solution?
- Can I do it with less code and changes?
- Does it work in all cases?


## 5. Submit a pull request to `main` branch
So far, you should have pushed your work to your feature branch and have tested
that it works on a real Android device.
The final step is to [open a pull request](https://github.com/Pranesh-Selvaraj/Ratio/pulls) to the `main` branch of the
official Ratio repo.

**[Submitting a PR - GitHub tutorial](https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/creating-a-pull-request-from-a-fork)**

### IMPORTANT:
- Make sure the base repository is set to `Pranesh-Selvaraj/Ratio` and its base is set to `main`.
- Pull requests to other branches will be rejected.
- Ratio doesn't have QA, so **you are the QA!** Please test your implementation carefully.

### Questions?
Ask them by opening an [issue](https://github.com/Pranesh-Selvaraj/Ratio/issues).

[![Issues](https://img.shields.io/github/issues/Pranesh-Selvaraj/Ratio)](https://github.com/Pranesh-Selvaraj/Ratio/issues)
