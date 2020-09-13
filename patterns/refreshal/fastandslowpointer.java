import java.util.*;

class FastAndSlowPointer {

    public static boolean isCycle(ListNode head) 
    {
        ListNode slow = head; 
        ListNode fast = head;


        while (fast != null) {
            slow = slow.next; 
            fast = fast.next.next; 

            if (slow == fast) return true; 
        }

        return false; 
    }

    public static ListNode startOfCycle(ListNode head) {
        // Provided a linkedlist has a cycle, 
        // The start of the cycle is gotten by 
        // finding the length of the cycle 
        // Thisi s essentiall the trip count  between the slow pointers after getting the cycle 
        // use length to calculate start 
        //      <---------------
        // a -> b -> c -> d -> e

        ListNode slow = head; 
        ListNode fast = head; 

        while (fast != null) {
            slow = slow.next; 
            fast = fast.next.next; 

            if (slow == fast) {
                return findStart(head, cycleLength(slow));
            }
        }

        throw new IllegalArgumentException("Not a Valid List with cycle");
    }

    public static int cycleLength(ListNode head) {

        ListNode slow = head; 
        ListNode current = head; 

        int length = 0; 

        do {
            length++;
            current = current.next; 
        } while (current != slow);

        return length; 
    }

    public static ListNode findStart(ListNode head, int length){
        ListNode pointerA = head; 
        ListNode pointerB = head; 

        while (length > 0) {
            pointerB = pointerB.next; 
            length--; 
        }

        while (pointerA != pointerB) {
            pointerA = pointerA.next; 
            pointerB = pointerB.next; 
        }

        return pointerA; 
    }

    public static void rearrangeLinkedlist(ListNode head)
    {
        /***
             * Given the head of a Singly LinkedList, write a method to modify the LinkedList such that the nodes from the second half of the LinkedList are inserted alternately to the nodes from the first half in reverse order. So if the LinkedList has nodes 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null, your method should return 1 -> 6 -> 2 -> 5 -> 3 -> 4 -> null.

            Your algorithm should not use any extra space and the input LinkedList should be modified in-place.

            Example 1:

            Input: 2 -> 4 -> 6 -> 8 -> 10 -> 12 
            Output: 2 -> 12 -> 4 -> 10 -> 6 -> 8 

            2 4 6 8 10 12 
            .  -
               .    -
                  .     -
                    .        -

            2  4  6        8  10  12 

                           12 10  8 

            
            2 4 6          8 -> null 
                           
            2 -> 12 -> 4 -> 10 -> 6 -> 8 

            2 12 8 10 
            10 8 12 2
         */

         if (head == null || head.next == null) throw new IllegalArgumentException("Invalid linkedklist node");

         ListNode otherHalf = reverse(middleOfLinkedlist(head));

         ListNode current = head; 
         ListNode otherHalfCurrent = otherHalf;

         ListNode firstHalfNext = null;
         ListNode otherHalfNext = null;

        while (otherHalfCurrent.next != null) {
            firstHalfNext = current.next; 
            otherHalfNext = otherHalfCurrent.next; 

            current.next = otherHalfCurrent;
            otherHalfCurrent.next = firstHalfNext;

            otherHalfCurrent = otherHalfNext;
            current = firstHalfNext;
        }
    }

    public static ListNode middleOfLinkedlist(ListNode head) {
        ListNode slow = head; 
        ListNode fast = head; 

        while (fast != null && slow != null) {
            slow = slow.next; 
            fast = fast.next.next; 
        }

        return slow; 
    }

    public static ListNode reverse(ListNode head) {
        ListNode current = head; 
        ListNode previous = null; 
        ListNode next = null; 

        while (current != null) {
            next = current.next; 
            current.next = previous; 
            previous = current;

            current = next;
        }

        return previous;
    }

    public static void main(String[] args) {

        // ListNode head = new ListNode(4);
        // head.next = new ListNode(3); 
        // head.next.next = new ListNode(10);
        // head.next.next.next = new ListNode(20);
        // head.next.next.next.next = head.next;
        // System.out.println("LinkedList has cycle: " + FastAndSlowPointer.isCycle(head));

        // ListNode head = new ListNode(14);
        // head.next = new ListNode(3); 
        // head.next.next = new ListNode(10);
        // head.next.next.next = new ListNode(20);
        // head.next.next.next.next = head.next;

        // ListNode start = startOfCycle(head);

        // System.out.println(start.value);

        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(8);
        head.next.next.next.next = new ListNode(10);
        head.next.next.next.next.next = new ListNode(12);
        FastAndSlowPointer.rearrangeLinkedlist(head);
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
    }
}

class ListNode {

    int value = 0; 
    ListNode next;

    public ListNode(int value) {
        this.value = value; 
    }
}