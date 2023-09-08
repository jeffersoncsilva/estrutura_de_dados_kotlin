package chapter6

import chapter5.QueueImpl


typealias Visitor<T> = (TreeNode<T>) -> Unit

class TreeNode<T>(val value: T) {
    private val children: MutableList<TreeNode<T>> = mutableListOf()

    fun add(child: TreeNode<T>) = children.add(child)

    fun forEachDepthFirst(visit: Visitor<T>){
        visit(this)
        children.forEach {
            it.forEachDepthFirst(visit)
        }
    }

    fun forEachLeverOrder(visit: Visitor<T>){
        visit(this)
        val queue = QueueImpl<TreeNode<T>>()
        children.forEach {
            queue.enqueue(it)
        }

        var node = queue.dequeue()
        while(node != null){
            visit(node)
            node.children.forEach{ queue.enqueue(it)}
            node = queue.dequeue()
        }
    }

    fun search(value: T): TreeNode<T>?{
        var result: TreeNode<T>? = null
        forEachLeverOrder {
            if(it.value == value){
                result = it
            }
        }
        return result
    }
}