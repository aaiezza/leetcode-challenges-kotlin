import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class IsAnagramTest {
    private lateinit var subject: IsAnagram

    @BeforeEach
    fun setUp() {
        subject = IsAnagram()
    }

    @TestFactory
    fun `should return expected result`() = listOf(
        "rail safety" to "fairy tales" to true,
        "race car" to "race car" to true,
        "race cars" to "race cars" to true,
        "aaabbbccc" to "bbbaaaccc" to true,
        "aaabbbccc" to "bbbaaacc" to false,
        "fun" to "times" to false,
    )
        .map { (input, expected) ->
            DynamicTest.dynamicTest("${if(expected) 'T' else 'F'}: `${input.first}` & `${input.second}`") {
                assertThat(subject.isAnagram(input.first, input.second)).isEqualTo(expected)
            }
        }
}