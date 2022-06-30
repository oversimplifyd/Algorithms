package algorithms;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class ReverseLinkedList {

    public static class LinkedListNode {

        public int value;
        public LinkedListNode next;

        public LinkedListNode(int value) {
            this.value = value;
        }
    }

    /*public static LinkedListNode reverse(LinkedListNode headOfList) {

        // reverse the linked list in place
        LinkedListNode previous = null; 
        
        while (headOfList != null) {
            LinkedListNode temp = headOfList.next;
            headOfList.next = previous; 
            previous = headOfList; 
            
            if (temp == null) {
                return headOfList;
            }
            
            headOfList = temp;
        }

        return headOfList;
    }*/
    
    /**
     * More Intuitive, cleaner version 
     * 
     */
    public static LinkedListNode reverse(LinkedListNode headOfList) {
        
        LinkedListNode currentNode = headOfList; 
        LinkedListNode previousNode = null; // Sentinel Value 
        LinkedListNode nextNode = null; 
        
        while (currentNode != null) {
            
            nextNode = currentNode.next; 
            currentNode.next = previousNode; 
            previousNode = currentNode; 
            
            currentNode = nextNode; 
        }
        
        //Be careful not to retrun the Sentinel. 
        return previousNode; 
        
    }

    // tests

    @Test
    public void shortLinkedListTest() {
        final LinkedListNode[] nodes = valuesToLinkedListNodes(new int[] {1, 2});
        final LinkedListNode result = reverse(nodes[0]);
        assertTrue(isListReversed(result, nodes));
    }

    @Test
    public void longLinkedListTest() {
        final LinkedListNode[] nodes = valuesToLinkedListNodes(new int[] {1, 2, 3, 4, 5, 6});
        final LinkedListNode result = reverse(nodes[0]);
        assertTrue(isListReversed(result, nodes));
    }

    @Test
    public void oneElementLinkedListTest() {
        final LinkedListNode node = new LinkedListNode(1);
        final LinkedListNode result = reverse(node);
        assertSame(node, result);
    }

    @Test
    public void emptyLinkedListTest() {
        final LinkedListNode result = reverse(null);
        assertNull(result);
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

    private static boolean isListReversed(LinkedListNode list, LinkedListNode[] originalNodes) {
        int i = originalNodes.length - 1;
        while (list != null && i >= 0) {
            if (originalNodes[i] != list) {
                return false;
            }
            list = list.next;
            i--;
        }
        return list == null && i == -1;
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ReverseLinkedList.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}