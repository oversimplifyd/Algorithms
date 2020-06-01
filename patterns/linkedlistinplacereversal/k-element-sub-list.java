package algorithms; 

/** 
Given the head of a LinkedList and a number ‘k’, reverse every ‘k’ sized sub-list starting from the head.
If, in the end, you are left with a sub-list with less than ‘k’ elements, reverse it too.
Runtime O(N) 
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

    // mySolution -> Inefficient! O(N^2) 
    // int length = 0; 
    // ListNode current = head; 

    // while (current != null) {
    //   current = current.next;
    //   length++; 
    // }

    // if (length < 2 || k <=2 || head == null) {
    //   throw new IllegalArgumentException("Invalid Initial Size"); 
    // }

    // int start = 1; 

    // do {
    //   int reverseLength = Math.min(length, k); 
    //   head = reverseSubList(head, start, reverseLength);  
    //   length = length - k;
    //   start = start + k; 
    // } while (length > 0); 

    // O(N) 
    if (k <= 1 || head == null) {
          return head;
    }
    
    ListNode current = head; 
    ListNode previous = null; 

    while (true) {

      ListNode nodeBeforeSubList = previous; 
      ListNode headOfSubList = current; 

      // For iteration only 
      //current = headOfSubList; 
      //previous = nodeBeforeSubList; 
      ListNode next = null; 

      for (int i = 0; current != null && i < k; i++) {
        next = current.next; 
        current.next = previous; 
        previous = current;

        current = next; 
      }

      ListNode nodeAfterSubList = current; 

      // rejoins the rearranged sublilst to the initial part 
      if (nodeBeforeSubList != null) {
        nodeBeforeSubList.next = previous; 
      } else {
        head = previous; 
      }

      headOfSubList.next = nodeAfterSubList; 

      if (current == null) {
        break; 
      }

      previous = headOfSubList;
    }

    return head;
  }

  // private static ListNode reverseSubList(ListNode head, int start, int length) {

  //   ListNode nodeBeforeTargetSubList = null; 
  //   ListNode current = head; 

  //   int i = 0;

  //   while (current != null && i < start - 1) {
  //     nodeBeforeTargetSubList = current;
  //     current = current.next; 

  //     i++; 
  //   }

  //   ListNode previous = null; 
  //   ListNode next = null; 
  //   ListNode currentNode = current;
  //   ListNode headOfSubListBeforeReversal = current; 

  //   // O(K) 
  //   while (currentNode != null && length > 0) {
  //     next = currentNode.next; 
  //     currentNode.next = previous;
  //     previous = currentNode; 

  //     currentNode = next; 
  //     length--; 
  //   }

  //   // rejoining the sublist 
  //   if (nodeBeforeTargetSubList != null) {
  //     nodeBeforeTargetSubList.next = previous;
  //   } else {
  //     head = previous; 
  //   }

  //   // rejoin the second half
  //   headOfSubListBeforeReversal.next = currentNode; 

  //   return head; 
  // }

  public static void main(String[] args) {
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);
    head.next.next.next.next.next = new ListNode(6);
    head.next.next.next.next.next.next = new ListNode(7);
    head.next.next.next.next.next.next.next = new ListNode(8);

    ListNode result = ReverseEveryKElements.reverse(head, 3);
    System.out.print("Nodes of the reversed LinkedList are: ");
    while (result != null) {
      System.out.print(result.value + " ");
      result = result.next;
    }
  }
}