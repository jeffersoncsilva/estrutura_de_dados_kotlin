package chapter7

import java.lang.Integer.max

typealias Visitor<T> = (T) -> Unit

class BinaryNode<T : Comparable<T>>(var value: T) {
    var leftChild : BinaryNode<T>? = null
    var rightChild: BinaryNode<T>? = null

    val min: BinaryNode<T>?
        get() = leftChild?.min ?: this

    val isBinarySearchTree: Boolean
        get() = isBST(this, min = null, max = null)

    override fun toString() = diagram(this)

    private fun diagram(node: BinaryNode<T>?, top: String = "", root: String = "", bottom: String = ""): String{
        return node?.let{
            if(node.leftChild == null && node.rightChild == null){
                "$root${node.value}\n"
            }else {
                diagram(node.rightChild, "$top ", "$top ┌──", "$top| ") + root + "${node.value}\n" +
                        diagram(node.leftChild, "$bottom| ", "$bottom└──", "$bottom ")
            }
        } ?: "${root}null\n"
    }

    fun traverseInOrder(visit: Visitor<T>){
        leftChild?.traverseInOrder(visit)
        visit(value)
        rightChild?.traverseInOrder(visit)
    }

    fun traversePreOrder(visit: Visitor<T>){
        visit(value)
        leftChild?.traversePreOrder(visit)
        rightChild?.traversePreOrder(visit)
    }

    fun traversePostOrder(visit: Visitor<T>){
        leftChild?.traversePostOrder(visit)
        rightChild?.traversePostOrder(visit)
        visit(value)
    }

    fun height(node: BinaryNode<T>? = this): Int{
        return node?.let{
            1 + max(height(node.leftChild), height(node.rightChild))
        } ?: -1
    }

    fun traversePreOrderWithNull(visit: Visitor<T>){
        visit(value)
        leftChild?.traversePreOrderWithNull(visit)
        rightChild?.traversePreOrderWithNull(visit)
    }

    fun serialize(node: BinaryNode<T> = this): MutableList<T?>{
        val list = mutableListOf<T?>()
        node.traversePreOrderWithNull { list.add(it) }
        return list
    }

    fun deserialize(list: MutableList<T?>):BinaryNode<T>?{
        val rootValue = list.removeAt(list.size - 1) ?: return null
        val root = BinaryNode<T>(rootValue)
        root.leftChild = deserialize(list)
        root.rightChild = deserialize(list)
        return root
    }

    private fun isBST(tree: BinaryNode<T>?, min: T?, max: T?) : Boolean{
        tree ?: return true
        if(min != null && tree.value <= min){
            return false
        } else if (max != null && tree.value > max){
            return false
        }
        return isBST(tree.leftChild, min, tree.value) && isBST(tree.rightChild, tree.value, max)
    }
}
