package algorithms; 

class ListNode {
  int value = 0;
  ListNode next;

  ListNode(int value) {
    this.value = value;
  }
}

class RearrangeList {

  public static void reorder(ListNode head) {

    if (head == null || head.next == null)
      return;
      
    ListNode fast = head; 
    ListNode slow = head;   

    while (fast != null && slow != null) {
      fast = fast.next.next; 
      slow = slow.next; 
    }

    ListNode reversedSecondHalf = reverse(slow); 

    ListNode nextNode = null; 
    ListNode secondHalfNextNode = null; 

    ListNode current = head; 
    ListNode secondHalfCurrent = reversedSecondHalf; 

    while (secondHalfCurrent.next != null) {
      nextNode = current.next; 
      secondHalfNextNode = secondHalfCurrent.next; 
      
      current.next = secondHalfCurrent;
      secondHalfCurrent.next = nextNode; 

      secondHalfCurrent = secondHalfNextNode; 
      current = nextNode; 
    }

    if (current != null) {
      current = null; 
    }
  }


  private static ListNode reverse(ListNode head) {

    ListNode current = head; 
    ListNode previousNode = null; 
    ListNode nextNode = null; 

    while  (current != null) {
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
    head.next.next.next = new ListNode(8);
    head.next.next.next.next = new ListNode(10);
    head.next.next.next.next.next = new ListNode(12);
    RearrangeList.reorder(head);
    while (head != null) {
      System.out.print(head.value + " ");
      head = head.next;
    }
  }
}