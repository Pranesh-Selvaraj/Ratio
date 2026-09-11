package ratio.automate.base.github

import arrow.core.Either
import ratio.automate.base.github.model.GitHubComment
import ratio.automate.base.github.model.GitHubIssue
import ratio.automate.base.github.model.GitHubIssueNumber
import ratio.automate.base.github.model.GitHubLabel
import ratio.automate.base.github.model.GitHubPAT
import ratio.automate.base.github.model.GitHubUsername
import ratio.automate.base.github.model.NotBlankTrimmedString

interface GitHubService {
    suspend fun fetchIssue(
        issueNumber: GitHubIssueNumber
    ): Either<Throwable, GitHubIssue>

    suspend fun fetchIssueLabels(
        issueNumber: GitHubIssueNumber
    ): Either<Throwable, List<GitHubLabel>>

    suspend fun fetchIssueComments(
        issueNumber: GitHubIssueNumber
    ): Either<Throwable, List<GitHubComment>>

    suspend fun commentIssue(
        pat: GitHubPAT,
        issueNumber: GitHubIssueNumber,
        text: NotBlankTrimmedString
    ): Either<Throwable, Unit>

    suspend fun assignIssue(
        pat: GitHubPAT,
        issueNumber: GitHubIssueNumber,
        assignee: GitHubUsername
    ): Either<Throwable, Unit>
}
