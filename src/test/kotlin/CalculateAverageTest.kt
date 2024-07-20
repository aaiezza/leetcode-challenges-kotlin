import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class CalculateAverageTest {
    private lateinit var subject: CalculateAverage

    @BeforeEach
    fun setUp() {
        subject = CalculateAverage()
    }

    @TestFactory
    fun `should return expected result`() = listOf(
        arrayOf(3, 4, 7, 9) to 5.75 to true,
        arrayOf(1, 2, 3, 4) to 2.5 to true,
        arrayOf(3, 5, 9, 7, 11, 14) to 8.0 to false,
    )
        .map { (inputs, expected) ->
            val input = inputs.first.toIntArray() to inputs.second
            DynamicTest.dynamicTest("CalculateAverage should return $expected") {
                assertThat(subject.hasAverage(input.second, *input.first)).isEqualTo(expected)
            }
        }
}
