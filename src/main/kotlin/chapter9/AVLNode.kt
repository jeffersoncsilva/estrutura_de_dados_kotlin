package chapter9

import chapter7.BinaryNode
import java.lang.Math.max

class AVLNode<T: Comparable<T>>(var value: T) {
    var leftChild: AVLNode<T>? = null
    var rightChilt: AVLNode<T>? = null

    var height = 0

    val leftHeight: Int
        get() = leftChild?.height ?: -1

    val rightHeight: Int
        get() = rightChilt?.height ?: -1

    val balanceFactor: Int
        get() = leftHeight - rightHeight

    private fun leftRotate(node: AVLNode<T>): AVLNode<T>{
        val pivot = node.rightChilt!!
        node.rightChilt = pivot.leftChild
        pivot.leftChild = node
        node.height = max(node.leftHeight, node.rightHeight) + 1
        pivot.height = max(pivot.leftHeight, pivot.rightHeight) + 1
        return pivot
    }

    private fun rightRotate(node: AVLNode<T>) : AVLNode<T>{
        val pivot = node.leftChild!!
        node.leftChild = pivot.rightChilt
        pivot.rightChilt = node
        node.height = max(node.leftHeight, node.rightHeight) + 1
        pivot.height = max(pivot.leftHeight, pivot.rightHeight) + 1
        return pivot
    }

    private fun rightLeftRotate(node: AVLNode<T>) : AVLNode<T>{
        val rightChild = node.rightChilt ?: return node
        node.rightChilt = rightRotate(rightChild)
        return leftRotate(node)
    }

    private fun leftRightRotate(node: AVLNode<T>) : AVLNode<T>{
        val leftChild = node.leftChild ?: return node
        node.leftChild = leftRotate(leftChild)
        return rightRotate(node)
    }

    private fun balanced(node: AVLNode<T>): AVLNode<T>{
        return when(node.balanceFactor){
            2 -> {
                if(node.leftChild?.balanceFactor == -1){
                    leftRightRotate(node)
                }else
                    rightRotate(node)
            }
            -2 -> {
                if(node.rightChilt?.balanceFactor == 1){
                    rightLeftRotate(node)
                }else {
                    leftRotate(node)
                }
            }
            else -> node
        }
    }

    private fun insert(node: AVLNode<T>?, value: T) : AVLNode<T>?{
        node ?: return AVLNode(value)
        if(value < node.value){
            node.leftChild = insert(node.leftChild, value)
        }else {
            node.rightChilt = insert(node.rightChilt, value)
        }
        val balancedNode = balanced(node)
        balancedNode?.height = max(balancedNode?.leftHeight ?: 0, balancedNode?.rightHeight ?: 0) + 1
        return balancedNode
    }

}