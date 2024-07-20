import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class DigitFrequencyTest {
    private lateinit var subject: DigitFrequency

    @BeforeEach
    fun setUp() {
        subject = DigitFrequency()
    }

    @TestFactory
    fun `should return expected result of string numbers`() = listOf(
        "123" to "321" to true,
        "1123" to "234" to false,
        "562435" to "234556" to true
    )
        .map { (input, expected) ->
            DynamicTest.dynamicTest("$input → $expected") {
                assertThat(subject.equalDigitFrequencyString(input.first, input.second)).isEqualTo(expected)
            }
        }


    @TestFactory
    fun `should return expected result of numbers`() = listOf(
        123 to 321 to true,
        1123 to 234 to false,
        562435 to 234556 to true
    )
        .map { (input, expected) ->
            DynamicTest.dynamicTest("$input → $expected") {
                assertThat(subject.equalDigitFrequency(input.first, input.second)).isEqualTo(expected)
            }
        }
}
