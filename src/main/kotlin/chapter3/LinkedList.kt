package chapter3

class LinkedList<T> : Iterable<T>, Collection<T>, MutableIterable<T>, MutableCollection<T>{
    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    override var size = 0
        private set

    override fun clear() {
        head = null
        tail = null
        size = 0
    }

    override fun addAll(elements: Collection<T>): Boolean {
        for(el in elements){
            append(el)
        }
        return true
    }

    override fun add(element: T): Boolean {
        append(element)
        return true
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        for(searched in elements){
            if(!contains(searched)) return false
        }
        return true
    }

    override fun contains(element: T): Boolean {
        for(iten in this){
            if (iten == element) return true
        }
        return false
    }

    override fun isEmpty() : Boolean{
        return size == 0
    }

    override fun iterator(): MutableIterator<T> {
        return LinkedListInterator(this)
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        var result = false
        val iterator = this.iterator()
        while(iterator.hasNext()){
            val item = iterator.next()
            if(!elements.contains(item)){
                iterator.remove()
                result = true
            }
        }
        return result
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        var result = false
        for(item in elements){
            result = remove(item) || result
        }
        return result
    }

    override fun remove(element: T): Boolean {
        val iterator = iterator()
        while(iterator.hasNext()){
            val item = iterator.next()
            if(item == element){
                iterator.remove()
                return true
            }
        }
        return false
    }

    override fun toString(): String {
        if(isEmpty())
            return "Empty list"
        else
            return head.toString()
    }

    fun push(value: T) : LinkedList<T>{
        head = Node(value = value, next = head)
        if(tail == null)
            tail = head
        size++
        return this
    }

    fun append(value: T){
        if(isEmpty()) {
            push(value)
            return
        }
        tail?.next = Node(value = value)
        tail = tail?.next
        size++
    }

    fun nodeAt(idx: Int) : Node<T>?{
        var currentNode = head
        var currentIdx = 0
        while(currentNode != null && currentIdx < idx){
            currentNode = currentNode.next
            currentIdx++
        }
        return currentNode
    }

    fun insert(value: T, afterNode: Node<T>) : Node<T>{
        if(tail == afterNode){
            append(value)
            return tail!!
        }
        val newNode = Node(value = value, next = afterNode.next)
        afterNode.next = newNode
        size++
        return newNode
    }

    fun pop(): T?{
        if(!isEmpty()) size--
        val result = head?.value
        head = head?.next
        if (isEmpty()){
            tail = null
        }
        return result
    }

    fun removeLast(): T?{
        val head = head ?: return null
        if(head.next == null) return pop()
        size--
        var prev = head
        var current = head
        var next = current.next
        while(next != null){
            prev = current
            current = next
            next = current.next
        }
        prev.next = null
        tail = prev
        return current.value
    }

    fun removeAfter(node: Node<T>): T? {
        val restul = node.next?.value
        if(node.next == tail){
            tail = node
        }
        if(node.next != null){
            size--
        }
        node.next = node.next?.next
        return restul
    }
}

fun <T> LinkedList<T>.printReverse() {
    var size = this.size
    for( i in size downTo 0){
        this.nodeAt(i)?.let {
            print( "${it.value} ")
        }
    }
}

fun <T> LinkedList<T>.middle(): T? {
    if(this.size == 0)
        return null
    var size = this.size / 2
    return this.nodeAt(size)!!.value
}

fun <T> Node<T>.printInReverse(){
    this.next?.printInReverse()
    if(this.next != null){
        print(" -> ")
    }
    print(this.value.toString())
}

