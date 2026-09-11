package ratio.automate.base.model

import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.matchers.shouldBe
import ratio.automate.base.github.model.GitHubUsername
import org.junit.Test

class GitHubUsernameTest {
    @Test
    fun `invalid username`() {
        GitHubUsername.from("").shouldBeLeft()
        GitHubUsername.from(" ").shouldBeLeft()
        GitHubUsername.from("  ").shouldBeLeft()
    }

    @Test
    fun `valid username`() {
        // given
        val rawUsername = " Pranesh-Selvaraj "

        // when
        val res = GitHubUsername.from(rawUsername)

        // then
        res.shouldBeRight().value shouldBe "Pranesh-Selvaraj"
    }
}
