import java.util.*; 

class KWayMerge {

    public static ListNode mergeLinkedList(ListNode[] items) {
        /**
         * Given an array of ‘K’ sorted LinkedLists, merge them into one sorted list.

            Example 1:

            Input: L1=[2, 6, 8], L2=[3, 6, 7], L3=[1, 3, 4]
            Output: [1, 2, 3, 3, 4, 6, 6, 7, 8]

            h
            2  6  8 
            
            h
            3  6  7 

            h
            1  3  4 

            1  2  3 

            1 
         */

         if (items.length == 0) throw new IllegalArgumentException("Argument can not be empty");

         PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a,b) -> a.value - b.value);

         // Adds the head of every node to the list 
         for (int i = 0; i < items.length; i++) {
             minHeap.offer(items[i]);
         }

         ListNode resultHead = null; 
         ListNode resultTail = null; 

         while (!minHeap.isEmpty()) {
             // Poll the first node, which is the head of one of the lists 
             // make it the resultHead, resultTail 
             // pick the next node of THIS node and add itto PriroityQueue

             ListNode currentNode = minHeap.poll(); //smallest item which is also the head of a list 
             if (resultHead == null) {
                 resultHead = resultTail = currentNode;  // assign the head to the current node if it is null 
             } else {
                 // since we have a head, we should modify our tail next to point to the new node instead 
                 resultTail.next = currentNode; 
                 resultTail = resultTail.next; 
             }

             if (currentNode.next != null) {
                 minHeap.offer(currentNode.next);
             }
         }

         return resultHead;
    }

    public static int kSmallestNumber(List<Integer[]> items, int k) {
        /**
         * Given ‘M’ sorted arrays, find the K’th smallest number among all the arrays.

            Example 1:

            Input: L1=[2, 6, 8], L2=[3, 6, 7], L3=[1, 3, 4], K=5

            2 6 8 9
            3 6 7
            1 3 4 

             8 7

             1 2 3 3 4 6 6 

            Output: 4
            Explanation: The 5th smallest number among all the arrays is 4, this can be verified from the merged 
            list of all the arrays: [1, 2, 3, 3, 4, 6, 6, 7, 8]
         */

         PriorityQueue<Node> minHeap = new PriorityQueue<>(k, (a,b) -> items.get(a.arrayIndex)[a.elementIndex] - items.get(b.arrayIndex)[b.elementIndex]);

         for (int i = 0; i < items.size(); i++) {
             if (items.get(i) != null && items.get(i).length > 0)
                minHeap.offer(new Node(0, i));
         }

         int numberCount = 0;  int result = 0;

         while (!minHeap.isEmpty()) {
             Node node = minHeap.poll();
             result = items.get(node.arrayIndex)[node.elementIndex];
             if (++numberCount == k) break; 
             if (items.get(node.arrayIndex).length > node.elementIndex + 1) {
                 minHeap.add(new Node(node.elementIndex + 1, node.arrayIndex));
             }
         }

         return result; 
    }
    
    public static void main(String[] args) {
        // ListNode l1 = new ListNode(2);
        // l1.next = new ListNode(6);
        // l1.next.next = new ListNode(8);

        // ListNode l2 = new ListNode(3);
        // l2.next = new ListNode(6);
        // l2.next.next = new ListNode(7);

        // ListNode l3 = new ListNode(1);
        // l3.next = new ListNode(3);
        // l3.next.next = new ListNode(4);

        // ListNode result = KWayMerge.mergeLinkedList(new ListNode[] { l1, l2, l3 });
        // System.out.print("Here are the elements form the merged list: ");
        // while (result != null) {
        //     System.out.print(result.value + " ");
        //     result = result.next;
        // }

        Integer[] l1 = new Integer[] { 2, 6, 8 };
        Integer[] l2 = new Integer[] { 3, 6, 7 };
        Integer[] l3 = new Integer[] { 1, 3, 4 };
        List<Integer[]> lists = new ArrayList<Integer[]>();
        lists.add(l1);
        lists.add(l2);
        lists.add(l3);
        int result = KWayMerge.kSmallestNumber(lists, 5);
        System.out.print("Kth smallest number is: " + result);
    }
}

class ListNode {
    int value; 
    ListNode next; 

    public ListNode(int value) {
        this.value = value;
    }
}

class Node {
    int elementIndex; 
    int arrayIndex; 

    public Node(int elementIndex, int arrayIndex) {
        this.elementIndex = elementIndex;
        this.arrayIndex = arrayIndex;
    }
}
