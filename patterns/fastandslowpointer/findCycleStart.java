package algorithms; 

class ListNode {
  int value = 0;
  ListNode next;

  ListNode(int value) {
    this.value = value;
  }
}

class LinkedListCycleStart {

  public static ListNode findCycleStart(ListNode head) {
    
    ListNode slow = head; 
    ListNode fast = head; 

    int cycleLength = 0;

    while (fast != null && fast.next != null) {

      fast = fast.next.next; 
      slow = slow.next; 

      if (fast == slow) {
        // We have a cycle 
        // find cycle Length. NB: This pointer might not necessarily be the start of the cycle 
        // This is just the point we knew fast pointer met slow pointer which means we are in a cycle 
        cycleLength = getCycleLength(slow);
        break;

      }
    }
    return findStart(head, cycleLength);
  }

  private static int getCycleLength(ListNode slowPointer) {
    int count = 0; 
    ListNode current = slowPointer; 

    do {
      count++; 
      current = current.next; 
    } while (current != slowPointer);

    return count; 
  }

  private static ListNode findStart(ListNode head, int cycleLength) {

    ListNode pointer1 = head; 
    ListNode pointer2 = head;

    // move pointer 2 K nodes ahead. where K is the length of the cycle 
    while (cycleLength > 0) {
      pointer2 = pointer2.next; 
      cycleLength--; 
    }

    while (pointer1 != pointer2) {
      pointer2 = pointer2.next; 
      pointer1 = pointer1.next; 
    }

    return pointer1; 
  }

  public static void main(String[] args) {
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);
    head.next.next.next.next.next = new ListNode(6);

    head.next.next.next.next.next.next = head.next.next;
    System.out.println("LinkedList cycle start: " + LinkedListCycleStart.findCycleStart(head).value);

    head.next.next.next.next.next.next = head.next.next.next;
    System.out.println("LinkedList cycle start: " + LinkedListCycleStart.findCycleStart(head).value);

    head.next.next.next.next.next.next = head;
    System.out.println("LinkedList cycle start: " + LinkedListCycleStart.findCycleStart(head).value);
  }
}