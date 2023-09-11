package chapter12

import java.util.*
import kotlin.collections.ArrayList

abstract class AbstractHeap<T>() : Heap<T> {
    var elements: ArrayList<T> = ArrayList<T>()

    override val count: Int
        get() = elements.size

    abstract fun compare(a: T, b: T) : Int

    override fun peek(): T? = elements.firstOrNull()

    override fun insert(element: T){
        elements.add(element)
        siftUp(count - 1)
    }

    override fun remove(): T?{
        if(isEmpty) return null

        Collections.swap(elements, 0, count - 1)
        val item  = elements.removeAt(count - 1)
        siftDown(0)
        return item
    }

    override fun remove(index: Int): T?{
        if (index >= count) return null

        return if(index == count -1){
            elements.removeAt(count - 1)
        } else{
            Collections.swap(elements, index, count-1)
            val item = elements.removeAt(count - 1)
            siftDown(index)
            siftUp(index)
            item
        }
    }

    override fun isMinHeap(): Boolean {
        if (isEmpty) return true
        (count / 2 - 1 downTo 0).forEach{
            val left = leftChildIndex(it)
            val right = rightChildIndex(it)
            if( left < count && compare(elements[left], elements[it]) < 0)
                return false
            if (right < count && compare(elements[right], elements[it]) < 0)
                return false
        }
        return true
    }

    protected fun heapify(values: ArrayList<T>){
        elements = values
        if(elements.isNotEmpty()){
            (count / 2 downTo 0).forEach{
                siftDown(it)
            }
        }
    }

    private fun siftUp(index: Int){
        var child = index
        var parent = parentIndex(index)
        while(child > 0 && compare(elements[child], elements[parent]) > 0){
            Collections.swap(elements, child, parent)
            child = parent
            parent = parentIndex(child)
        }
    }

    private fun siftDown(index: Int){
        var parent = index
        while(true){
            val left = leftChildIndex(index)
            val right = rightChildIndex(index)
            var candidate = parent
            if(left < count && compare(elements[left], elements[candidate]) > 0){
                candidate = left
            }
            if(right < count && compare(elements[right], elements[candidate]) > 0){
                candidate = right
            }
            if(candidate == parent)
                return
            Collections.swap(elements, parent, candidate)
            parent = candidate

        }
    }

    private fun index(element: T, i: Int) : Int?{
        if( i>= count)
            return null
        if(compare(element, elements[i]) > 0)
            return null
        if(element == elements[i])
            return i
        val leftChildIndex = index(element, leftChildIndex(i))
        if(leftChildIndex != null) return leftChildIndex
        val rightChildIndex = index(element, rightChildIndex(i))
        if(rightChildIndex != null) return rightChildIndex

        return null
    }

    private fun leftChildIndex(index: Int) = (2 * index) + 1

    private fun rightChildIndex(index: Int) = (2 * index) + 2

    private fun parentIndex(index: Int) = (index - 1) / 2

    private fun buildHeap(){
        if(elements.isNotEmpty()){
            (count / 2 downTo 0).forEach{
                siftUp(it)
            }
        }
    }
}