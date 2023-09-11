package chapter12

interface Heap<T>:Collection<T> {
    fun peek(): T?

    //fun merge(heap: AbstractHeap<T>)

    fun isMinHeap(): Boolean
}