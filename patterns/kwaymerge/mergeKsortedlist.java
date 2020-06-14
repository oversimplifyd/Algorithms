package algorithms; 

/**
Given an array of ‘K’ sorted LinkedLists, merge them into one sorted list.

Time Complexity -. O(N * LogK)
Space -> O(K) We only store K items at any point in time in MINHEAP 

An alternative but not as efficient solution would be to use mege sort technique 
This willl merge it two arrays at a time 
Time ->  O(kN)
 */

 import java.util.*;

class ListNode {
  int value;
  ListNode next;

  ListNode(int value) {
    this.value = value;
  }
}

class MergeKSortedLists {

  public static ListNode merge(ListNode[] lists) {
    ListNode result = new ListNode(-1);
    PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a,b) -> a.value - b.value);

    for (ListNode root: lists) { //KlogK K arrays[] 
      if (root != null) {
        minHeap.offer(root);
      }
    }

    ListNode resultHead = null; 
    ListNode resultTail = null;

    // N * Log K -> We will eventially have all the elements in all arrays added to the heap which will make logK traversal at each poin 
    while (!minHeap.isEmpty()) { 
      ListNode currentNode = minHeap.poll();

      if (resultHead == null) {
        resultHead = currentNode; 
        resultTail = resultHead;
      } else {
        resultTail.next = currentNode;
        resultTail = resultTail.next; 
      }

      if (currentNode.next != null) {
        minHeap.offer(currentNode.next);
      }
    }

    return resultHead;
  }

  public static void main(String[] args) {
    ListNode l1 = new ListNode(2);
    l1.next = new ListNode(6);
    l1.next.next = new ListNode(8);

    ListNode l2 = new ListNode(3);
    l2.next = new ListNode(6);
    l2.next.next = new ListNode(7);

    ListNode l3 = new ListNode(1);
    l3.next = new ListNode(3);
    l3.next.next = new ListNode(4);

    ListNode result = MergeKSortedLists.merge(new ListNode[] { l1, l2, l3 });
    System.out.print("Here are the elements form the merged list: ");
    while (result != null) {
      System.out.print(result.value + " ");
      result = result.next;
    }
  }
}
