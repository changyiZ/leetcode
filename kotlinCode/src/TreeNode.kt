import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.max

class TreeNode(var `val`: Int = 0) {
    var left: TreeNode? = null
    var right: TreeNode? = null

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val treeNode = buildTree(arrayOf(9,3,15,20,7).toIntArray(), arrayOf(9,15,7,20,3).toIntArray())
            print(treeNode)
        }
    }
}

fun preorderTraversal(root: TreeNode?): List<Int> {
    val treeList = ArrayList<Int>()
    val nodeStack = Stack<TreeNode>()

    root?.let {
        nodeStack.push(it)
        var node: TreeNode
        while (nodeStack.isNotEmpty()) {
            node = nodeStack.pop()
            treeList.add(node.`val`)
            node.right?.let {
                nodeStack.push(it)
            }
            node.left?.let {
                nodeStack.push(it)
            }
        }
    }
    return treeList
}

fun inorderTraversal(root: TreeNode?): List<Int> {
    val treeList = ArrayList<Int>()
    val nodeStack = Stack<TreeNode>()

    root?.let {
        var node: TreeNode = it
        node.right?.let {
            nodeStack.push(it)
            node.right = null
        }
        nodeStack.push(it)
        node.left?.let {
            nodeStack.push(it)
            node.left = null
        }
        while (nodeStack.isNotEmpty()) {
            node = nodeStack.pop()
            if (node.left == null) {
                treeList.add(node.`val`)
            } else {
                node.right?.let {
                    nodeStack.push(it)
                    node.right = null
                }
                nodeStack.push(node)
                nodeStack.push(node.left)
                node.left = null
            }
        }
    }
    return treeList
}

fun postorderTraversal(root: TreeNode?): List<Int> {
    val treeList = ArrayList<Int>()
    val nodeStack = Stack<TreeNode>()

    root?.let {
        var node: TreeNode = it
        nodeStack.push(it)
        node.right?.let {
            nodeStack.push(it)
            node.right = null
        }
        node.left?.let {
            nodeStack.push(it)
            node.left = null
        }
        while (nodeStack.isNotEmpty()) {
            node = nodeStack.pop()
            if (node.left == null && node.right == null) {
                treeList.add(node.`val`)
            } else {
                nodeStack.push(node)
                node.right?.let {
                    nodeStack.push(it)
                    node.right = null
                }
                node.left?.let {
                    nodeStack.push(it)
                    node.left = null
                }
            }
        }
    }
    return treeList
}

fun levelOrder(root: TreeNode?): List<List<Int>> {
    val treeList = ArrayList<List<Int>>()
    root?.let {
        var subTreeNodeList = ArrayList<Int>()
        val nodeQueue = LinkedList<TreeNode>()
        nodeQueue.add(it)
        var levelCount = nodeQueue.size
        var node: TreeNode
        while (nodeQueue.isNotEmpty()) {
            if (levelCount == 0) {
                levelCount = nodeQueue.size
                subTreeNodeList = ArrayList()
            }
            node = nodeQueue.pollFirst()
            subTreeNodeList.add(node.`val`)
            if (--levelCount == 0) {
                treeList.add(subTreeNodeList)
            }
            node.left?.let {
                nodeQueue.addLast(it)
            }
            node.right?.let {
                nodeQueue.addLast(it)
            }
        }
    }
    return treeList
}

fun maxDepth(root: TreeNode?): Int {
    if (root == null) {
        return 0
    }
    return max(maxDepth(root.left), maxDepth(root.right)) + 1
}

fun isSymmetric(root: TreeNode?): Boolean {
    root?.let {
        val queue = LinkedList<TreeNode?>()
        queue.addLast(it)
        queue.addLast(it)

        var left: TreeNode?
        var right: TreeNode?
        while (queue.isNotEmpty()) {
            left = queue.pollFirst()
            right = queue.pollFirst()
            if (left?.`val` != right?.`val`) {
                return false
            }
            left?.let {
                queue.addLast(it.left)
            }
            right?.let {
                queue.addLast(it.right)
                queue.addLast(it.left)
            }
            left?.let {
                queue.addLast(it.right)
            }
        }
    }
    return true
}

fun hasPathSum(root: TreeNode?, sum: Int): Boolean {
    if (root == null) {
        return false
    }
    val current = root.`val`
    if (root.left == null && root.right == null) {
        return current == sum
    }

    return hasPathSum(root.left , sum - current) || hasPathSum(root.right , sum - current)
}

fun buildTree(inorder: IntArray, postorder: IntArray): TreeNode? {
    if (inorder.isEmpty() || postorder.isEmpty()) {
        return null
    }

    val length = postorder.size
    val value = postorder[length - 1]
    val treeNode = TreeNode(value)
    val index = inorder.indexOf(value) // == Left size
    if (index > 0) {
        treeNode.left = buildTree(inorder.sliceArray(0 until index), postorder.sliceArray(0 until index))
    }
    if (index + 1 < length) {
        treeNode.right = buildTree(inorder.sliceArray(index + 1 until length), postorder.sliceArray(index until length - 1))
    }
    return  treeNode
}