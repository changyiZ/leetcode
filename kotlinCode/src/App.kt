import java.util.*
import kotlin.collections.ArrayList

fun main(args: Array<String>) {
    print("Ready to Code~")
}

/**
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int = 0) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class TreeNode(var `val`: Int = 0) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
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
        val subTreeNodeList = ArrayList<Int>()
        val nodeQueue = ArrayList<TreeNode>()

        root?.let {
            nodeQueue.add(it)
            while (nodeQueue.isNotEmpty()) {

            }

            subTreeNodeList.add(it.`val`)
            treeList.add(subTreeNodeList)
            it.left?.let {
                nodeQueue.add(it)
            }
            it.right?.let {
                nodeQueue.add(it)
            }
        }

        return treeList
    }
}