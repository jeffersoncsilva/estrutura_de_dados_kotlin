package chapter12

class ComparatorHeapImpl<T>(private val comparator: Comparator<T>) : AbstractHeap<T>() {
    companion object{
        fun <Element> create(elements: ArrayList<Element>, comparator: Comparator<Element>): Heap<Element>{
            val heap = ComparatorHeapImpl(comparator)
            heap.heapify(elements)
            return heap
        }
    }

    override fun compare(a: T, b: T) : Int = comparator.compare(a, b)
}