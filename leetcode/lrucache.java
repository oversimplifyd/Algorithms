package algorithms; 

import java.util.*; 

// Map -> key:query, value:Node 
// Node -> query, result 
// Node is going to belong to a linkedlist 

// get() -> search the hashMap if it exists, get the node and return the node's value 
// update / move_to_front -> get the nOde, manipulate it's pointers such that it becomes the head 
// remove_from_tail -> the node to be removed previous as to point to null 

// scope, why? use cases, constraints 
// high level design -> estimation, system API design 
// core componenent Design 
// scaling the arch 
// bottlenecks and tradeoffs

class LRUCache {

    Node head, tail; 
    Map<String, Node> map; 
    int capacity; 
    int size; 

    public LRUCache(int cap) {
        this.capacity = cap; 
        this.size = 0; 

         this.amp = new HashMap<>();
    }

    public String put(String key, String value) {

        if (this.size == this.capacity) {
            this.removeFromTail();
        }

        Node nodeToAdd = this.moveToFront(new Node(value, key, null, null));
        map.put(key, nodeToAdd);

        return value; 
    }

    public String get(String key) {
        if (this.map.containsKey(key)) {
            return this.map.get(key).value;
        }

        return null; 
    }

    public Node moveToFront(Node node) {
        Node currentPrev = node.prev; 
        Node currentNext = node.enxt; 

        currentPrev.next = currentNext;
        currentNext.prev = currentPrev;

        node.next = this.head; 
        node.prev = null;
        this.head.prev = node; 

        this.head  = node; 

        return head; 
    }

    public void removeFromTail() {
        this.map.remove(this.tail.key);

        Node tailPrev = this.tail.prev; 
        tailPrev.next = null; 
        this.tail = tailPrev;
    }
}

class Node {

    Node next; 
    Node prev; 

    String value; 
    String key; 

    public Node(String value, String key, Node next, Node prev) {
        this.next = next; 
        this.prev = prev; 
        this.value = value; 
        this.key = key; 
    }
}
