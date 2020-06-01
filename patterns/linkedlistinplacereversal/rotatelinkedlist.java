package algorithms; 

/**
Given the head of a Singly LinkedList and a number ‘k’, rotate the LinkedList to the right by ‘k’ nodes.
 */

import java.util.*;

class ListNode {
  int value = 0;
  ListNode next;

  ListNode(int value) {
    this.value = value;
  }
}

class RotateList {

  public static ListNode rotate(ListNode head, int rotations) {

    ListNode headOfList = head; 
    ListNode lastNode = null; 

    int length = 1; 
    // O(N) 
    while (headOfList.next != null) {
      headOfList = headOfList.next; 
      lastNode = headOfList; 
      length++; 
    }

    rotations = rotations % length; 
    int skipLength = length - rotations; 

    ListNode current = head; 

    while (skipLength > 1 && current != null) {
      current = current.next;
      skipLength--; 
    }

    ListNode partToRotate = current.next; 
    current.next = null; 

    lastNode.next = head; 
    head = partToRotate; 

    return head;
  }

  public static void main(String[] args) {
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);
    head.next.next.next.next.next = new ListNode(6);

    ListNode result = RotateList.rotate(head, 3);
    System.out.print("Nodes of the reversed LinkedList are: ");
    while (result != null) {
      System.out.print(result.value + " ");
      result = result.next;
    }
  }
}