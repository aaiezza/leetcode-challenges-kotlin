import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class ListChunkerTest {
    private lateinit var subject: ListChunker

    @BeforeEach
    fun setUp() {
        subject = ListChunker()
    }

    private val inputs = listOf(
        Arguments(listOf(1, 2, 3, 4), 2u) to listOf(listOf(1, 2), listOf(3, 4)),
        Arguments(listOf(1, 2, 8, 3, 6, 4), 3u) to listOf(listOf(1, 2, 8), listOf(3, 6, 4)),
        Arguments(listOf(10, 20, 30, 40, 50), 2u) to listOf(listOf(10, 20), listOf(30, 40), listOf(50)),
    )

    @TestFactory
    fun `should return expected results`() = inputs
        .map { (input, expected) ->
            DynamicTest.dynamicTest("$input → $expected") {
                assertThat(subject.chunkList(input.input, input.sizeOfChunks)).isEqualTo(expected)
            }
        }


    @TestFactory
    fun `should return expected results using indicies function`() = inputs
        .map { (input, expected) ->
            DynamicTest.dynamicTest("$input → $expected") {
                assertThat(subject.chunkListUsingIndicies(input.input, input.sizeOfChunks)).isEqualTo(expected)
            }
        }

    data class Arguments<T>(val input: List<T>, val sizeOfChunks: UInt)
}