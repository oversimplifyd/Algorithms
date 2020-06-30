package algorithms; 

/**
Check if a LinkedLit is palindrome. 
Runtime O(N) 
Space O(1) 


Common Usage
- If there's a ycle 
- Length of a cycle 
- Start of a cycle 
- middle node
- kth to last node 
 */

class ListNode {
  int value = 0;
  ListNode next;

  ListNode(int value) {
    this.value = value;
  }
}

class PalindromicLinkedList {

  public static boolean isPalindrome(ListNode head) {
    // TODO: Write your code here
   // Get the mid point.  0(N) 
   // Reverse the linkedlist at the midpoint  o(N)
   // iteratively check using two pointer if the start corresponds to the end O(N) 

   ListNode fast = head; 
   ListNode slow = head; 

   while (fast != null && fast.next != null) {
     fast = fast.next.next; 
     slow = slow.next; 
   }

   ListNode secondHalf = reverse(slow); 
   ListNode copyOfMiddleNode = secondHalf; 

   ListNode current = head; 

   while (secondHalf != null) {

     if (current.value != secondHalf.value) {
       break; 
     }
     current = current.next; 
     secondHalf = secondHalf.next; 
   }

   reverse(copyOfMiddleNode);
   if (secondHalf == null) return true; 

   return false; 
  }

  private static ListNode reverse(ListNode head) {
    ListNode current = head; 
    ListNode previousNode = null; 
    ListNode nextNode = null; 

    while (current != null) {
      nextNode = current.next; 
      current.next = previousNode; 
      previousNode = current; 
      current = nextNode; 
    }

    return previousNode; 
  }

  public static void main(String[] args) {
    ListNode head = new ListNode(2);
    head.next = new ListNode(4);
    head.next.next = new ListNode(6);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(2);
   System.out.println("Is palindrome: " + PalindromicLinkedList.isPalindrome(head));

    head.next.next.next.next.next = new ListNode(2);
    System.out.println("Is palindrome: " + PalindromicLinkedList.isPalindrome(head));
  }
}