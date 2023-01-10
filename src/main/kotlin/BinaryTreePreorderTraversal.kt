class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    fun preorderTraversal(root: TreeNode?): List<Int> {
        val nodes = mutableListOf<Int>()
        val route = ArrayDeque<TreeNode>()
        root?.let { route.add(it) }

        while (!route.isEmpty()) {
            val currentNode = route.removeLast()
            nodes += currentNode.`val`
            currentNode.right?.let { route.add(it) }
            currentNode.left?.let { route.add(it) }
        }

        return nodes
    }
}
