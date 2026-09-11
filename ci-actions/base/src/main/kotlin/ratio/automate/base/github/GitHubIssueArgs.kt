package ratio.automate.base.github

import arrow.core.Either
import arrow.core.raise.either
import ratio.automate.base.Constants
import ratio.automate.base.ensureArgument
import ratio.automate.base.github.model.GitHubIssueNumber
import ratio.automate.base.github.model.GitHubPAT
import ratio.automate.base.parseAsMap

data class GitHubIssueArgs(
    val pat: GitHubPAT,
    val issueNumber: GitHubIssueNumber
)

fun parseArgs(argsList: List<String>): Either<String, GitHubIssueArgs> = either {
    val args = argsList.parseAsMap()

    val gitHubPAT = args.ensureArgument(Constants.ARG_GITHUB_PAT)
    val issueId = args.ensureArgument(Constants.ARG_ISSUE_NUMBER)

    GitHubIssueArgs(
        pat = GitHubPAT.from(gitHubPAT).bind(),
        issueNumber = GitHubIssueNumber.from(issueId).bind(),
    )
}