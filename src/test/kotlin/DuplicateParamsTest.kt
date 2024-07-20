import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class DuplicateParamsTest {
    private lateinit var subject: DuplicateParams

    @BeforeEach
    fun setUp() {
        subject = DuplicateParams()
    }

    @TestFactory
    fun `should return expected results`() = listOf(
        arrayOf("a", "a", "b", "c", "d") to listOf("a"),
        arrayOf("a", "c", "d", "e", "f") to emptyList(),
    )
        .map { (input, expected) ->
            DynamicTest.dynamicTest("${input.contentToString()} → $expected") {
                assertThat(subject.getDuplicateParams(*input)).isEqualTo(expected)
            }
        }
}
