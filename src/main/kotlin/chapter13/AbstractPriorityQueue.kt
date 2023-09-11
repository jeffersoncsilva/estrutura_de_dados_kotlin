package chapter13

import chapter12.ComparableHeapImpl
import chapter12.ComparatorHeapImpl
import chapter12.Heap

abstract class AbstractPriorityQueue<T: Any> : Queue<T>{
    abstract val heap: Heap<T>
        get
    override val count: Int
        get() = heap.count

    override fun enqueue(t: T): Boolean{
        heap.insert(t)
        return true
    }

    override fun dequeue(): T? = heap.remove()

    override fun peek() = heap.peek()
}

class ComparablePriorityQueueImpl<T: Comparable<T>>: AbstractPriorityQueue<T>(){
    override val heap = ComparableHeapImpl<T>()
}

class ComparatorPriorityQueueImpl<T: Any>(private val comparator: Comparator<T>) :AbstractPriorityQueue<T>(){
    override val heap = ComparatorHeapImpl(comparator)
}