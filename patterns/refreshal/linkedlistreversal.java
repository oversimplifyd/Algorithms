import java.util.*; 

class LinkedListReversal {

    public static ListNode reverse(ListNode head) {

        ListNode current = head; 
        ListNode previous = null; 
        ListNode next = null; 

        // 1 -> 2 -> 3 -> 4 
        // 4 -> 3 -> 2 -> 1 -> null
        while (current != null) {
            next = current.next; 
            current.next = previous; 
            previous = current; 

            current = next; 
        }

        return previous; 
    }

    public static ListNode reverseSublist(ListNode head, int p, int q){
        /**
         * Given the head of a LinkedList and two positions ‘p’ and ‘q’, reverse the LinkedList from position ‘p’ to ‘q’.
         * 
         * 1  2    3  4  5    6  7  8  9
         * 1  2    5  4  3    6  7  8  9
         *  (p-1) 
         * q+1 node 
         * 
         * 
         * 
         * p = 2 
         * q = 4
         * 
         */

         // 1  2    3  4  5    6  7  8  9
         // 3, 6
         // 1 2     3 4 5      6 7 8 9 

         // 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8
         //           p         q
         // 1 -> 2 -> | 3  4  5 -> | 6 -> 7 -> 8 
         //           | 5 -> 4 -> 3 | 6 -> 7 -> 8 
         //  6 -> ... 
         //  2 -> ... 

         if (q == p) return head; 

         ListNode current = head; 
         ListNode previous = null; 

         for(int i = 0; i < p - 1 && current != null; i++) {
             previous = current;
             current = current.next; 
         }

         ListNode lastNodeOfFirstPart = previous;  // 2
         ListNode startNode = current;  // 3
         ListNode next = null; 

         for (int j = 0; j < q - p + 1 && current != null; j++) {
             next = current.next; 
             current.next = previous;

             previous = current;
             current = next;
         }

         if (lastNodeOfFirstPart != null) {
             lastNodeOfFirstPart.next = previous;
         } else {
             head = previous; 
         }

         startNode.next = current;

         return head; 
    }

    public static ListNode reverseKSublist(ListNode head) {
        /**
         * Given the head of a LinkedList and a number ‘k’, reverse every ‘k’ sized sub-list starting from the head.

            If, in the end, you are left with a sub-list with less than ‘k’ elements, reverse it too.

            1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8

            K= 3 
            3 -> (save) 
            1 -> 

            startOfKlist 
            endOfKList 
            3 -> 2 -> 1 -> 6 -> 5 -> 4 -> null 

            4
         */


    }

    public static void main(String[] args) {
        // ListNode head = new ListNode(2);
        // head.next = new ListNode(4);
        // head.next.next = new ListNode(6);
        // head.next.next.next = new ListNode(8);
        // head.next.next.next.next = new ListNode(10);
    
        // ListNode result = LinkedListReversal.reverse(head);
        // System.out.print("Nodes of the reversed LinkedList are: ");
        // while (result != null) {
        //   System.out.print(result.value + " ");
        //   result = result.next;
        // }

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
    
        ListNode result = LinkedListReversal.reverseSublist(head, 2, 3);
        System.out.print("Nodes of the reversed LinkedList are: ");
        while (result != null) {
          System.out.print(result.value + " ");
          result = result.next;
        }
    }
}

class ListNode {
    int value; 
    ListNode next; 

    public ListNode(int value) {
        this.value = value; 
    }
}
