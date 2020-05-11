package algorithms;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class KthTolastNode {

    public static class LinkedListNode {

        public int value;
        public LinkedListNode next;

        public LinkedListNode(int value) {
            this.value = value;
        }
    }
    
    
    public static LinkedListNode kthToLastNode(int k, LinkedListNode head) {

        // return the kth to last node in the linked list
        if (k == 0) {
            throw new IllegalArgumentException("Invalid");
        }
        
        LinkedListNode currentNode = head; 
        
        int listSize = 0; 
        
        while (currentNode != null) {
            listSize++; 
            currentNode = currentNode.next; 
        }
        
        if (k > listSize) {
            throw new IllegalArgumentException("Invalid");
        }
        
        int currentIndex = 0; 
        
        currentNode = head;
        
        while (currentIndex < (listSize - k)) {
            currentNode = currentNode.next;
            currentIndex++; 
        }
        
        return currentNode;
    }

    //econdApproach 
    //Using two pointers 
    // One starts at k 
    //The other starts at head and keeps moving until the one that starts at k gets to the end. 
    
    
    public static LinkedListNode kthToLastNode2(int k, LinkedListNode head) {

        // return the kth to last node in the linked list
        if (k == 0) {
            throw new IllegalArgumentException("Invalid");
        }
        
        LinkedListNode rightNode = head; 
        
        int listSize = 0; 
        
        for (int i = 0; i < k  - 1; i++) {
            //Make right node starts at K 
             if (rightNode.next == null) {
                    throw new IllegalArgumentException(
                            "k is larger than the length of the linked list: " + k);
             }
             
            rightNode = rightNode.next; 
        }
        
        LinkedListNode leftNode = head; 
        
        while (rightNode.next != null) {
            leftNode = leftNode.next; 
            rightNode = rightNode.next; 
        }
        
        return leftNode; 
    }
    
    // tests

    @Test
    public void firstToLastNodeTest() {
        final LinkedListNode[] listNodes = valuesToLinkedListNodes(new int[] {1, 2, 3, 4});
        final int k = 1;
        final LinkedListNode actual = kthToLastNode(k, listNodes[0]);
        final LinkedListNode expected = listNodes[listNodes.length - k];
        assertSame(expected, actual);
    }

    @Test
    public void secondToLastNodeTest() {
        final LinkedListNode[] listNodes = valuesToLinkedListNodes(new int[] {1, 2, 3, 4});
        final int k = 2;
        final LinkedListNode actual = kthToLastNode(k, listNodes[0]);
        final LinkedListNode expected = listNodes[listNodes.length - k];
        assertSame(expected, actual);
    }

    @Test
    public void firstNodeTest() {
        final LinkedListNode[] listNodes = valuesToLinkedListNodes(new int[] {1, 2, 3, 4});
        final int k = 4;
        final LinkedListNode actual = kthToLastNode(k, listNodes[0]);
        final LinkedListNode expected = listNodes[listNodes.length - k];
        assertSame(expected, actual);
    }

    @Test(expected = Exception.class)
    public void kIsGreaterThanLinkedListLengthTest() {
        final LinkedListNode[] listNodes = valuesToLinkedListNodes(new int[] {1, 2, 3, 4});
        final int k = 5;
        kthToLastNode(k, listNodes[0]);
    }

    @Test(expected = Exception.class)
    public void kIsZeroTest() {
        final LinkedListNode[] listNodes = valuesToLinkedListNodes(new int[] {1, 2, 3, 4});
        final int k = 0;
        kthToLastNode(k, listNodes[0]);
    }

    private static LinkedListNode[] valuesToLinkedListNodes(int[] values) {
        final LinkedListNode[] nodes = new LinkedListNode[values.length];
        for (int i = 0; i < values.length; i++) {
            nodes[i] = new LinkedListNode(values[i]);
            if (i > 0) {
                nodes[i - 1].next = nodes[i];
            }
        }
        return nodes;
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(KthTolastNode.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}