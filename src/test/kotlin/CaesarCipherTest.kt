import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class CaesarCipherTest {
    private lateinit var subject: CaesarCipher

    @BeforeEach
    fun setUp() {
        subject = CaesarCipher()
    }

    @TestFactory
    fun `should return expected result`() = listOf(
        "abc" to 1 to "bcd",
        "def" to 3 to "ghi",
        "lmnop" to 8 to "tuvwx"
    )
        .map { (input, expected) ->
            DynamicTest.dynamicTest("`${input.first}` + ${input.second} → `$expected`") {
                assertThat(subject.encodeCaesarCipher(input.first, input.second)).isEqualTo(expected)
            }
        }
}