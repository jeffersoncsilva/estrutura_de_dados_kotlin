package chapter18

import chapter14.swapAt

fun<T: Comparable<T>> MutableList<T>.partitionDutchFlag(low: Int, high: Int, pivotIndex: Int): Pair<Int, Int>{
    val pivot = this[pivotIndex]
    var smaller = low
    var equal = low
    var larger = high
    while(equal <= larger){
        if(this[equal] < pivot){
            this.swapAt(smaller, equal)
            smaller += 1
            equal += 1
        }else if(this[equal] == pivot){
            equal += 1
        }else{
            this.swapAt(equal, larger)
            larger -= 1
        }
    }
    return Pair(smaller, larger)
}
