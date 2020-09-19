import java.util.*;

class Cache {

    public static void main(String[] args) {

        LRUCache cache = new LRUCache(3);
        cache.addToCache(4);
        cache.addToCache(6);
        cache.addToCache(8);
        cache.addToCache(9);
        
        System.out.println(cache.getLeastRecentlyUsed());
        System.out.println(cache.getMostRecentlyUsed());
    }
}

class LRUCache {

    /**
     * D
     * B
     * C
     * A
     * 
     * 
     * Cache Hit 
     * Cache Miss 
     * Access  (O1)
     * Removal when Cache is full  O(1)
     * Re-ordering when there is a hit 
     * 
     * get() -> O(1)
     * put() -> O(1) 
     *  
     * key -> value 
     * 
     * A  -> A 
     * B -> B
     * 
     * Miss: 
     * removeLast(): 
     *      unlink the node 
     *      remove from hasMap 
     *  null->A->null 
     * moveToHead()
        * head.previous = new Node 
        * newNode.next = formerHead
        * head = new Node
     * 
     * Hit: 
     * Lookup from Map 
     * re-order back to top 
     *   if(currentNode.preivous != null): 
     *         it's not the current top of the elements 
     *          moveToHead()
                    * head.previous = new Node 
                    * newNode.next = formerHead
                    * head = new Node
     *   else ignore 
     * 
     * 
     */
    
     ListNode head; 
     ListNode tail; 

     final int maxLength = 4;

     HashMap<Integer, ListNode> map; 

    public LRUCache(int value) {
        this.head = this.tail = new ListNode(value);
        this.map = new HashMap<>();   
        this.map.put(value, this.head);
    }

     public void addToCache(int value) {
         if (this.map.size() == this.maxLength) {
            this.map.remove(this.tail.value);
             this.removeLast();
         }

         if (this.map.containsKey(value)) {
             this.moveToTop(this.map.get(value));
         } else {
             ListNode node = new ListNode(value);
             this.map.put(value, node);
             this.moveToTop(this.map.get(value));
         }
     }

     /**
      * Gets the least recently used 
      */
     public int getLeastRecentlyUsed(){
         return this.tail.value;
     }

     public int getMostRecentlyUsed() {
         return this.head.value;
     }

     private void print(){
         ListNode current = this.head; 
         while (current != null) {
             System.out.println(current.value);
             current = current.next; 
         }
     }

     public void moveToTop(ListNode node){
         ListNode current = this.head; 
         current.previous = node; 
         node.next = current; 

         this.head = node; 
     }

     public void removeLast(){
         ListNode previous = this.tail.previous; 
         previous.next = null; 
         this.tail = previous; 
     }
}

class ListNode {

    int value; 
    ListNode previous; 
    ListNode next; 

    public ListNode(int value){
        this.value = value; 
    }
}

