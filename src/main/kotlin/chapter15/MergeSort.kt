package chapter15

fun <T: Comparable<T>> List<T>.mergeSort() : List<T>{
    if(this.size < 1)
        return this
    val middle = this.size / 2
    val left = this.subList(0, middle).mergeSort()
    val right = this.subList(middle, this.size).mergeSort()

    return  merge(left, right)
}

private fun <T: Comparable<T>> merge(left: List<T>, right: List<T>) : List<T> {
    var leftIndx = 0
    var rightIndex = 0

    var result = mutableListOf<T>()

    while(leftIndx < left.size && rightIndex < right.size){
        val leftEleemnt = left[leftIndx]
        val rightElement = right[rightIndex]
        if(leftEleemnt < rightElement){
            result.add(leftEleemnt)
            leftIndx += 1
        }else if(leftEleemnt > rightElement){
            result.add(rightElement)
            rightIndex += 1
        }else {
            result.add(leftEleemnt)
            leftIndx += 1
            result.add(rightElement)
            rightIndex += 1
        }
    }
    if(leftIndx < left.size){
        result.addAll(left.subList(leftIndx, left.size))
    }
    if(rightIndex < right.size)
        result.addAll(right.subList(rightIndex, right.size))

    return result
}