package algorithms; 

/**
Given the head of a LinkedList and two positions ‘p’ and ‘q’, reverse the LinkedList from position ‘p’ to ‘q’.
 */
 
import java.util.*;

class ListNode {
  int value = 0;
  ListNode next;

  ListNode(int value) {
    this.value = value;
  }
}

class ReverseSubList {

  public static ListNode reverse(ListNode head, int p, int q) {
    // TODO: Write your code here

    if (p == q) {
      return head; 
    }

    ListNode floorNode = null;  // p - 1 node 
    ListNode current = head; 

    int i = 0; 
    while (current != null && i < p - 1) {
      floorNode = current; 
      current = current.next; 
      i++; 
    }

    ListNode nextNode = null; 
    ListNode previousNode = null;

    ListNode currentNode = current;

    // pth node 
    ListNode startNode = currentNode; 

    while (p <= q && currentNode != null) {
      nextNode = currentNode.next; 
      currentNode.next = previousNode;
      previousNode = currentNode;

      currentNode = nextNode; 
      p++; 
    }

    if (floorNode != null) {
      floorNode.next = previousNode;
    } else {
      head = previousNode;
    }
    
    startNode.next = currentNode; 

    return head;
  }

  public static void main(String[] args) {
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);

    ListNode result = ReverseSubList.reverse(head, 2, 3);
    System.out.print("Nodes of the reversed LinkedList are: ");
    while (result != null) {
      System.out.print(result.value + " ");
      result = result.next;
    }
  }
}