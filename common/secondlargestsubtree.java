import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class SecondLargest {

    public static class BinaryTreeNode {

        public int value;
        public BinaryTreeNode left;
        public BinaryTreeNode right;

        public BinaryTreeNode(int value) {
            this.value = value;
        }

        public BinaryTreeNode insertLeft(int leftValue) {
            this.left = new BinaryTreeNode(leftValue);
            return this.left;
        }

        public BinaryTreeNode insertRight(int rightValue) {
            this.right = new BinaryTreeNode(rightValue);
            return this.right;
        }
    }

    public static int findSecondLargest(BinaryTreeNode rootNode) {
        
        //This destroys the input 
        // find the second largest item in the binary search tree
        if (rootNode == null || (rootNode.left == null && rootNode.right == null)) {
            throw new IllegalArgumentException("Invalid..");
        }
        
        boolean leftExamined = false; 
        int secondLargest = 0; 
        
        BinaryTreeNode currentNode = rootNode;
        
        while (currentNode.left != null || currentNode.right != null) {
            if (currentNode.right != null && !leftExamined) {
                secondLargest = currentNode.value; 
                currentNode = currentNode.right;
            } else if (currentNode.right != null && leftExamined) {
                secondLargest = currentNode.right.value;
                currentNode = currentNode.right;
            } else if (currentNode.left != null && !leftExamined) {
                secondLargest = currentNode.left.value;
                currentNode = currentNode.left;
                leftExamined = true;
            } else {
                return currentNode.value;
            }
        }
        
        return secondLargest;
    }

     //ICake's Approach. Divide and Conquer. Simplified.  
    public static int findSecondLargestICakeSolution(BinaryTreeNode rootNode) {
         if (rootNode == null || (rootNode.left == null && rootNode.right == null)) {
            throw new IllegalArgumentException("Invalid..");
        }
        
        BinaryTreeNode currentNode = rootNode; 
        
        while (true) {
            
            if (currentNode.right == null && currentNode.left != null) {
                return findLargest(curentNode.left);
            }
            
             if (currentNode.right != null && (currentNode.right.right == null && currentNode.right.left == null)) {
                 return currentNode.value; 
             }
             
             currentNode = currentNode.right;
        }
    }
    
    public static int findLargest(BinaryTreeNode rootNode) {
        
        if (rootNode == null) {
            throw new IllegalArgumentException("Invalid");
        }
        
        BinaryTreeNode currentNode = rootNode; 
        
        if (currentNode.right != null) {
            return findLargest(currentNode.right);
        }
        
        return currentNode.value;
    }

    // tests

    @Test
    public void findSecondLargestTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        final BinaryTreeNode a = root.insertLeft(30);
        a.insertLeft(10);
        a.insertRight(40);
        final BinaryTreeNode b = root.insertRight(70);
        b.insertLeft(60);
        b.insertRight(80);
        final int actual = findSecondLargest(root);
        final int expected = 70;
        assertEquals(expected, actual);
    }

    @Test
    public void largestHasLeftChildTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        final BinaryTreeNode a = root.insertLeft(30);
        a.insertLeft(10);
        a.insertRight(40);
        root.insertRight(70).insertLeft(60);
        final int actual = findSecondLargest(root);
        final int expected = 60;
        assertEquals(expected, actual);
    }

    @Test
    public void largestHasLeftSubtreeTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        final BinaryTreeNode a = root.insertLeft(30);
        a.insertLeft(10);
        a.insertRight(40);
        final BinaryTreeNode b = root.insertRight(70).insertLeft(60);
        b.insertLeft(55).insertRight(58);
        b.insertRight(65);
        final int actual = findSecondLargest(root);
        final int expected = 65;
        assertEquals(expected, actual);
    }

    @Test
    public void secondLargestIsRootNodeTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        final BinaryTreeNode a = root.insertLeft(30);
        a.insertLeft(10);
        a.insertRight(40);
        root.insertRight(70);
        final int actual = findSecondLargest(root);
        final int expected = 50;
        assertEquals(expected, actual);
    }

    @Test
    public void descendingLinkedListTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        root.insertLeft(40).insertLeft(30).insertLeft(20);
        final int actual = findSecondLargest(root);
        final int expected = 40;
        assertEquals(expected, actual);
    }

    @Test
    public void ascendingLinkedListTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        root.insertRight(60).insertRight(70).insertRight(80);
        final int actual = findSecondLargest(root);
        final int expected = 70;
        assertEquals(expected, actual);
    }

    @Test(expected = Exception.class)
    public void exceptionWithTreeThatHasOneNodeTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        findSecondLargest(root);
    }

    @Test(expected = Exception.class)
    public void exceptionWithEmptyTreeTest() {
        findSecondLargest(null);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(SecondLargest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}