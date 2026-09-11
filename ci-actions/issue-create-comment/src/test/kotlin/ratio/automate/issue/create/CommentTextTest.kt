package ratio.automate.issue.create

import io.kotest.matchers.shouldBe
import ratio.automate.base.Constants
import ratio.automate.base.github.model.GitHubIssue
import ratio.automate.base.github.model.GitHubIssueNumber
import ratio.automate.base.github.model.GitHubUser
import ratio.automate.base.github.model.GitHubUsername
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CommentTextTest {

    @Test
    fun `the comment text should look good`(): Unit = runTest {
        // given
        val issue = GitHubIssue(
            number = GitHubIssueNumber(value = "223"),
            creator = GitHubUser(GitHubUsername("user1")),
            assignee = null
        )

        // when
        val commentText = commentText(issue)

        // then
        commentText shouldBe """
            Thank you @user1 for raising Issue #223! 🚀
            What's next? Read our **[Contribution Guidelines](${Constants.CONTRIBUTING_URL}) 📚**.
            
            _Tagging @Pranesh-Selvaraj for review & approval 👀_
        """.trimIndent()
    }
}