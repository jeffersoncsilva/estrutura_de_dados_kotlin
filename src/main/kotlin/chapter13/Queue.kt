package chapter13

interface Queue<T: Any> {
    fun enqueue(t: T): Boolean
    fun dequeue(): T?
    val count: Int
        get
    val isEmpty: Boolean
        get() = count == 0
    fun peek(): T?
}