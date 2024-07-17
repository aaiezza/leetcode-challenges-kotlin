import assertk.assertThat
import assertk.assertions.isNotZero
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ConnectCrabBoardTests {
    private lateinit var subject: ConnectCrabBoard

    @BeforeEach
    fun setUp() {
        subject = ConnectCrabBoard()
    }

    @Test
    fun `should make move and yield new board`() {
        val moves : List<Move> = subject.getPossibleMoves()

        assertThat(moves.size).isNotZero()

        val response : ConnectCrabBoard = moves.first().let { subject.applyMove(it) }

        assertThat(response).isNotNull()
    }


}