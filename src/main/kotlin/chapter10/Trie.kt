package chapter10

import java.util.Collections
import kotlin.collections.Collection as Collection1

class Trie<Key> {
    private val root = TrieNode<Key>(key = null, parent = null)

    fun insert(list: List<Key>){
        var current = root

        list.forEach{ element ->
            if(current.children[element] == null){
                current.children[element] = TrieNode(element, current)
            }
            current = current.children[element]!!
        }
        current.isTerminating = true
    }

    fun contains(list: List<Key>) : Boolean{
        var current = root
        list.forEach { element ->
            val child = current.children[element] ?: return false
            current = child
        }
        return current.isTerminating
    }

//    fun remove(collection: Collections){
//        var current = root
//        collection.forEach{
//            val child = current.children[it] ?: return
//            current = child
//        }
//        if(!current.isTerminating) return
//        current.isTerminating = false
//
//        val parent = current.parent
//        while(current.children.isEmpty() && !current.isTerminating){
//            parent?.let{
//                it.children[current.key] = null
//                current = it
//            }
//        }
//    }

    fun collections(prefix: List<Key>): List<List<Key>> {
        // 1
        var current = root
        prefix.forEach { element ->
            val child = current.children[element] ?: return emptyList()
            current = child
        }
// 2
        return collections(prefix, current)
    }

    private fun collections(prefix: List<Key>, node:
    TrieNode<Key>?): List<List<Key>> {
// 1
        val results = mutableListOf<List<Key>>()
        if (node?.isTerminating == true) {
            results.add(prefix)
        }
// 2
        node?.children?.forEach { (key, node) ->
            results.addAll(collections(prefix + key, node))
        }
        return results
    }
}