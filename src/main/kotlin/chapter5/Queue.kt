package chapter5

interface Queue<T> {
    fun enqueue(element: T) : Boolean

    fun dequeue(): T?

    val count: Int
        get
    val isEmpty : Boolean
        get() = count == 0

    fun peek(): T?
}

class QueueImpl<T> : Queue<T>{

    private val list = arrayListOf<T>()

    override fun enqueue(element: T): Boolean {
        list.add(element)
        return true
    }

    override fun dequeue(): T? = if (isEmpty) null else list.removeAt(0)

    override val count: Int
        get() = list.size

    override fun peek(): T? = list.getOrNull(0)

    override fun toString(): String = list.toString()
}