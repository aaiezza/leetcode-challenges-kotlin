import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BinaryTreePreorderTraversalTests {
    private lateinit var subject: Solution

    @BeforeEach
    fun setUp() {
        subject = Solution()
    }

    @Test
    fun `should yield expected node list`() {
        val root = TreeNode(1)
        root.left = TreeNode(2)
        root.right = TreeNode(3)

        val response = subject.preorderTraversal(root)
        assertThat(response).isEqualTo(listOf(1, 2, 3))
    }
}
