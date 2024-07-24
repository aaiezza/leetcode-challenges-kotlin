import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.assertAll

class SublistCheckerTest {
    private lateinit var subject: SublistChecker

    @BeforeEach
    fun setUp() {
        subject = SublistChecker()
    }

    @TestFactory
    fun `should work as expected`() = listOf(
        Arguments("go home", "ome") to true,
        Arguments("InterviewDOT", "DOT") to true,
        Arguments("tow truck", " trc") to false,
        Arguments("Beach", "Be") to true,
        Arguments("follow the leader", "the l") to true,
        Arguments("follow the leader", "the a") to false,
    )
        .map { (input, expected) ->
            DynamicTest.dynamicTest("$input → $expected") {
                assertAll (
                    { assertThat(subject.isSublist(input.input, input.targetSubstring)).isEqualTo(expected) },
                    { assertThat(subject.isSublistUsingContains(input.input, input.targetSubstring)).isEqualTo(expected) }
                )
            }
        }

    data class Arguments(val input: String, val targetSubstring: String)
}
