import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class SumZeroTest {
    private lateinit var subject: SumZero

    @BeforeEach
    fun setUp() {
        subject = SumZero()
    }

    @TestFactory
    fun `should return expected result`() = listOf(
        listOf(-3, -2, 0, 1, 2) to (-2 to 2),
        listOf(-3, -2, 0, 1, 3) to (-3 to 3),
        listOf(-3, -2, 0, 1, 6) to null,
    )
        .map { (input, expected) ->
            DynamicTest.dynamicTest("$input → $expected") {
                assertThat(subject.sumZero(input)).isEqualTo(expected)
            }
        }
}