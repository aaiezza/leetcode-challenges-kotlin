import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.*

class MaxSublistSumTest {
    private lateinit var subject: MaxSublistSum

    @BeforeEach
    fun setUp() {
        subject = MaxSublistSum()
    }

    @TestFactory
    fun `should return expected results from sublist search`() = listOf(
        Arguments(intArrayOf(4, 2, 7), 2) to 9,
        Arguments(intArrayOf(4, 2, 7, 4), 3) to 13,
        Arguments(intArrayOf(4, 2, 7, 4), 1) to 7,
        Arguments(intArrayOf(-4, 2, -7), 2) to -2,
    )
        .map { (args, expected) ->
            DynamicTest.dynamicTest("$args → $expected") {
                assertThat(subject.maxSublistSum(args.nums, args.sublistSize)).isEqualTo(expected)
            }
        }

    @TestFactory
    fun `should return expected results from combo search`() = listOf(
        Arguments(intArrayOf(4, 2, 7), 2) to 11,
        Arguments(intArrayOf(4, 2, 7, 4), 3) to 15,
        Arguments(intArrayOf(4, 2, 7, 4), 1) to 7,
        Arguments(intArrayOf(-4, 2, -7), 2) to -2,
    )
        .map { (args, expected) ->
            DynamicTest.dynamicTest("$args → $expected") {
                assertThat(subject.maxComboSum(args.nums, args.sublistSize)).isEqualTo(expected)
            }
        }

    @Test
    fun `should throw an exception for trying to choose combinations from an empty list`() {
        assertAll {
            assertThrows<IllegalArgumentException> { subject.maxSublistSum(intArrayOf(), 0) }
            assertThrows<IllegalArgumentException> { subject.maxComboSum(intArrayOf(), 0) }
        }
    }

    data class Arguments(val nums: IntArray, val sublistSize: Int) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Arguments

            if (!nums.contentEquals(other.nums)) return false
            if (sublistSize != other.sublistSize) return false

            return true
        }

        override fun hashCode(): Int {
            var result = nums.contentHashCode()
            result = 31 * result + sublistSize
            return result
        }
    }
}
