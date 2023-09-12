import chapter14.swapAt

fun<T: Comparable<T>> MutableList<T>.insertionSort(showPasse: Boolean = false){
    if(this.size < 2)
        return
    for(current in 1 until this.size){
        for(shifting in (1..current).reversed()){
            if(this[shifting] < this[shifting-1]){
                swapAt(shifting, shifting-1)
            }else{
                break
            }
        }
        if(showPasse)
            println(this)
    }
}