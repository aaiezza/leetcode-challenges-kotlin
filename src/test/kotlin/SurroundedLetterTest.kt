import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class SurroundedLetterTest {
    private lateinit var subject: SurroundedLetter

    @BeforeEach
    fun setUp() {
        subject = SurroundedLetter()
    }

    @TestFactory
    fun `should return expected results`() = listOf(
        "+a+" to true,
        "+a++" to true,
        "+++z+" to true,
        "+ab+" to false,
        "+a+ab+" to false,
        "*a****a*" to false,
    )
        .map { (input, expected) ->
            DynamicTest.dynamicTest("$input → $expected") {
                assertThat(subject.isSurroundedLetter(input)).isEqualTo(expected)
            }
        }
}