import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class SumAllTest {
    private lateinit var subject: SumAll

    @BeforeEach
    fun setUp() {
        subject = SumAll()
    }

    @TestFactory
    fun `should return expected results`() = listOf(
        Arguments(listOf(-3, 2, 0, -2, 1), 2u, 0) to listOf(listOf(2, -2)),
        Arguments(listOf(-3, 2, 0, -2, 1), 3u, 0) to listOf(listOf(-3, 2, 1), listOf(2, 0, -2)),
        Arguments(listOf(-3, 2, 0, -2, 1), 3u, -5) to listOf(listOf(-3, 0, -2)),
        Arguments(listOf(-3, 2, 0, -2, 1), 4u, -2) to listOf(listOf(-3, 2, -2, 1)),
        Arguments(listOf(-3, 2, 0, -2, 1), 5u, -2) to listOf(listOf(-3, 2, 0, -2, 1)),
        Arguments(listOf(-3, 2, 0, -2, 1), 1u, 4) to emptyList(),
    )
        .map { (input, expected) ->
            DynamicTest.dynamicTest("$input → $expected") {
                assertThat(subject.sumAll(input.numbers, input.numberOfOperands, input.targetSum)).isEqualTo(expected)
            }
        }

    private data class Arguments(val numbers: List<Int>, val numberOfOperands: UInt, val targetSum: Int)
}
