import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TwoSumTests {
    private lateinit var subject: TwoSumSolution

    @BeforeEach
    fun setUp() {
        subject = TwoSumSolution()
    }

    @Test
    fun `should yield expected zig zag 3 rows`() {
        val response = subject.convertToZigZag("PAYPALISHIRING", 3)

        assertThat(response).isEqualTo("PAHNAPLSIIGYIR")
    }

    @Test
    fun `should yield expected zig zag 1 row`() {
        val response = subject.convertToZigZag("AB", 1)

        assertThat(response).isEqualTo("AB")
    }
}
