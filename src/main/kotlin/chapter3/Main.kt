package chapter3

fun main(args: Array<String>) {
    val node1 = Node(value = 1)
    val node2 = Node(value = 2)
    val node3 = Node(value = 3)
    node1.next = node2
    node2.next = node3
    //println(node1)

    val list = LinkedList<Int>()
    list.push(3).push(2).push(1)
    //println(list)

    println("before inserting: $list")
    var middleNode = list.nodeAt(1)!!
//    for (i in 1..3) {
//        middleNode = list.insert(-1 * i, middleNode)
//    }
    //println("After inserting: $list")

        list.printReverse()

}