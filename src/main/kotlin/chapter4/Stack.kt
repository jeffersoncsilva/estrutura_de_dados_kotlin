package chapter4

interface Stack<Element> {

    fun push(elemtn: Element)

    fun pop() : Element?

    fun peek(): Element?

    val count: Int
        get
    val isEmpty: Boolean
        get() = count == 0
}

class StackImpl<T : Any>(override val count: Int) : Stack<T>{
    private val storage = arrayListOf<T>()

    override fun push(element: T){
        storage.add(element)
    }

    override fun pop(): T? {
        if(storage.size == 0)
            return null
        return storage.removeAt(storage.size - 1)
    }

    override fun peek(): T? {
        return if (isEmpty)
            null
        else
            storage.get(storage.size - 1)

    }

    override fun toString() = buildString {
        appendln("----top----")
        storage.asReversed().forEach{
            appendln("$it")
        }
        appendln("-----------")
    }
}