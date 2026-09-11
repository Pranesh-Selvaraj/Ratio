package ratio.automate.issue.create

import arrow.core.Either
import arrow.core.raise.Raise
import arrow.core.raise.either
import ratio.automate.base.Constants
import ratio.automate.base.RatioError
import ratio.automate.base.github.GitHubIssueArgs
import ratio.automate.base.github.GitHubService
import ratio.automate.base.github.GitHubServiceImpl
import ratio.automate.base.github.model.GitHubIssue
import ratio.automate.base.github.model.NotBlankTrimmedString
import ratio.automate.base.github.parseArgs
import ratio.automate.base.ktor.ktorClientScope
import kotlinx.coroutines.runBlocking

data class Context(
    val gitHubService: GitHubService,
) : GitHubService by gitHubService

fun main(args: Array<String>): Unit = runBlocking {
    ktorClientScope {
        val context = Context(
            gitHubService = GitHubServiceImpl(
                ktorClient = ktorClient,
            ),
        )
        with(context) {
            val result = execute(args).fold(
                ifLeft = { throw RatioError("TASK FAILED: $it") },
                ifRight = { "TASK SUCCESSFUL: $it" }
            )
            println("[ISSUE-ASSIGN] $result")
        }
    }
}

context(GitHubService)
private suspend fun execute(argsArr: Array<String>): Either<String, String> = either {
    val args = parseArgs(argsArr.toList()).bind()

    val issue = fetchIssue(args.issueNumber).mapLeft {
        "Failed to fetch Issue #${args.issueNumber.value}"
    }.bind()
    comment(args, commentText(issue))
}

fun commentText(
    issue: GitHubIssue
): String = buildString {
    append("Thank you @${issue.creator.username.value} for raising Issue #${issue.number.value}! \uD83D\uDE80")
    append("\n")
    val guidelinesUrl = "**[Contribution Guidelines](${Constants.CONTRIBUTING_URL}) \uD83D\uDCDA**"
    append("What's next? Read our $guidelinesUrl.")
    append("\n\n")
    append("_Tagging @${Constants.RATIO_ADMIN} for review & approval \uD83D\uDC40_")
}

context(Raise<String>, GitHubService)
private suspend fun comment(
    args: GitHubIssueArgs,
    text: String
): String {
    return commentIssue(
        pat = args.pat,
        issueNumber = args.issueNumber,
        text = NotBlankTrimmedString(text)
    ).mapLeft {
        "Failed to comment: $it"
    }.map {
        "Commented on Issue #${args.issueNumber.value}"
    }.bind()
}