package algorithms; 

/** 
Given the head of a LinkedList and a number ‘k’, reverse every alternating ‘k’ sized sub-list starting from the head.

If, in the end, you are left with a sub-list with less than ‘k’ elements, reverse it too.
 */
 
import java.util.*;

class ListNode {
  int value = 0;
  ListNode next;

  ListNode(int value) {
    this.value = value;
  }
}

class ReverseEveryKElements {

  public static ListNode reverse(ListNode head, int k) {
    // TODO: Write your code here

    if (head == null || k <=1) return head; 

    ListNode previous = null; 
    ListNode current = head; 

    while (true) {

      ListNode headOfSubList = current; 
      ListNode nodeBeforeSubList = previous; 

      ListNode next = null; 

      for (int i = 0; current != null && i < k; i++) {
        next = current.next; 
        current.next = previous; 
        previous = current; 

        current = next; 
      }

      if (nodeBeforeSubList != null) {
        nodeBeforeSubList.next = previous; 
      } else {
        head = previous; 
      }

      headOfSubList.next = current; 

      if (current == null) {
        break; 
      }

      // does the shifting.. 
       previous = headOfSubList; 
      for (int i = 0; headOfSubList != null && i < k; i++) {
        headOfSubList = headOfSubList.next; 
        previous = headOfSubList; 
      }

      current = headOfSubList.next; 

      if (current == null) {
        break; 
      }
    }
    return head; 
  }

  public static void main(String[] args) {
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);
    head.next.next.next.next.next = new ListNode(6);
    head.next.next.next.next.next.next = new ListNode(7);
    head.next.next.next.next.next.next.next = new ListNode(8);

    ListNode result = ReverseEveryKElements.reverse(head, 2);
    System.out.print("Nodes of the reversed LinkedList are: ");
    while (result != null) {
      System.out.print(result.value + " ");
      result = result.next;
    }
  }
}