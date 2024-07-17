import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.TestFactory

class ReverseStringTest {
    private lateinit var subject: ReverseString

    @BeforeEach
    fun setUp() {
        subject = ReverseString()
    }

    @TestFactory
    fun `should return expected result`() = listOf(
        "apple" to "elppa",
        "racecar" to "racecar",
        "totes" to "setot"
    )
        .map { (input, expected) ->
            DynamicTest.dynamicTest("`$input` → `$expected`") {
                assertThat(subject.reverseString(input)).isEqualTo(expected)
            }
        }
}