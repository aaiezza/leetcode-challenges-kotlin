import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class FizzBuzzTest {
    private lateinit var subject: FizzBuzz

    @BeforeEach
    fun setUp() {
        subject = FizzBuzz()
    }

    @TestFactory
    fun `should return expected result`() = listOf(
        5 to listOf(1, 2, "Fizz", 4, "Buzz")
    )
        .map { (input, expected) ->
            DynamicTest.dynamicTest("`$input` → `${expected}`") {
                assertThat(subject.fizzBuzz(input)).isEqualTo(expected)
            }
        }

}