package chapter18

import chapter14.swapAt

fun <T: Comparable<T>> MutableList<T>.partitionHoare(low: Int, high: Int): Int{
    val pivo = this[low]
    var i = low - 1
    var j = high + 1
    while(true){
        do{
            j -= 1
        }while(this[j] > pivo)
        do{
            i += 1
        }while(this[i] < pivo)
        if(i < j){
            this.swapAt(i, j)
        }else{
            return j
        }
    }
}

fun<T: Comparable<T>> MutableList<T>.quicksortHoare(low: Int, high: Int){
    if(low < high){
        val p = this.partitionHoare(low, high)
        this.quicksortHoare(low, p)
        this.quicksortHoare(p+1, high)
    }
}