import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEmpty
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.assertAll

class CalculateAverageOfNumbersTest {
    private lateinit var subject: CalculateAverageOfNumbers

    @BeforeEach
    fun setUp() {
        subject = CalculateAverageOfNumbers()
    }

    @TestFactory
    fun `should return expected results`() = listOf(
        Arguments(intArrayOf(5, 1, 3, 2, 7, 2, 4), 3u, 4.0) to
                listOf(listOf(5, 3, 4), listOf(1, 7, 4), listOf(3, 2, 7), listOf(3, 7, 2)),
        Arguments((0..100 step 7).toIntArray(), 11u, 385 / 9.0) to
                emptyList(),
        Arguments((0..50 step 5).toIntArray(), 10u, 24.5) to
                listOf(listOf(0, 5, 10, 15, 20, 25, 35, 40, 45, 50)),
    )
        .map { (input, expected) ->
            DynamicTest.dynamicTest("$input → $expected") {
                assertAll(
                    {
                        assertThat( subject.averages(input.numbers, input.numberOfOperands, input.targetAverage))
                            .isNotEmpty()
                    },
                    {
                        assertThat(subject.findAverages(input.numbers, input.numberOfOperands, input.targetAverage))
                            .isEqualTo(expected)
                    }
                )
            }
        }

    data class Arguments(val numbers: IntArray, val numberOfOperands: UInt, val targetAverage: Double) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Arguments

            if (!numbers.contentEquals(other.numbers)) return false
            if (numberOfOperands != other.numberOfOperands) return false
            if (targetAverage != other.targetAverage) return false

            return true
        }

        override fun hashCode(): Int {
            var result = numbers.contentHashCode()
            result = 31 * result + numberOfOperands.hashCode()
            result = 31 * result + targetAverage.hashCode()
            return result
        }
    }

    inline fun IntRange.toIntArray(): IntArray = this.asIterable().toList().toIntArray()
    inline fun IntProgression.toIntArray(): IntArray = this.asIterable().toList().toIntArray()
}