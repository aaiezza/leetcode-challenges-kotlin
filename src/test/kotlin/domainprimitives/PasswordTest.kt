package domainprimitives

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.hasMessage
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class PasswordTest {
    @Test
    fun `should create password`() {
        assertDoesNotThrow {
            val pass = Password("pass1234", "pass1234")
            assertThat(pass.value).isEqualTo("pass1234")
        }
    }

    @TestFactory
    fun `should fail to create password with leading or trailing whitespace`() = listOf(
        "  pass1234",
        "pass1234 ",
        "\tpass1234",
        "pass1234\t",
        "\npass1234",
        "pass1234\n",
    )
        .map { input ->
            DynamicTest.dynamicTest("$input should fail") {
                assertFailure { Password(input, input) }.hasMessage("Password cannot begin or end with whitespace")
            }
        }
}
