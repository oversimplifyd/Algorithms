package algorithms;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class CheckBalancedTree {
    
    public static class BinaryTreeNode {

        public int value;
        public BinaryTreeNode left;
        public BinaryTreeNode right;
        public BinaryTreeNode parentNode;
        
        public boolean isLeftVisited = false;
        public boolean isRightVisited = false;

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
        
        public void updateRight(boolean visited) {
            this.isRightVisited = visited;
        }
        
        public void updateLeft(boolean visited) {
            this.isLeftVisited = visited; 
        }
    }
    
    public static boolean isBalanced(BinaryTreeNode treeRoot) {
        
        int lastKnownLength = 0;
        
        int negativeDiff = 0; 
        int positiveDiff = 0;
        
        int min = 0;
        int max = 0;
        
        int currentNodeLengthFromRoot = 0; 
        
        BinaryTreeNode currentNode = treeRoot;
        
        if (treeRoot.left == null) {
            treeRoot.updateLeft(true);
        }
        
        if (treeRoot.right == null) {
            treeRoot.updateRight(true);
        }
        
        while ((treeRoot.isLeftVisited == false || treeRoot.isRightVisited == false) || currentNode.parentNode != null) {
            
            if (currentNode.left != null && currentNode.isLeftVisited == false) {
                currentNode.updateLeft(true);
                BinaryTreeNode tempNode = currentNode;
                currentNode = tempNode.left;
                currentNode.parentNode = tempNode;
                currentNodeLengthFromRoot++;
            } else if (currentNode.right != null && currentNode.isRightVisited == false) {
                currentNode.updateRight(true);
                BinaryTreeNode tempNode = currentNode;
                currentNode = tempNode.right;
                currentNode.parentNode = tempNode;
                currentNodeLengthFromRoot++;
            } else {
                  
                if (currentNode.right == null && currentNode.left == null) {                    
                    if (currentNodeLengthFromRoot > max) {
                        max = currentNodeLengthFromRoot;
                    }
                    
                    if (min == 0) {
                        if (currentNodeLengthFromRoot <= max) {
                            min = currentNodeLengthFromRoot;
                        }
                    } else {
                         if (currentNodeLengthFromRoot < min) {
                            min = currentNodeLengthFromRoot;
                        }
                    }
                }
                BinaryTreeNode tempNode = currentNode;
                currentNode = tempNode.parentNode;
                currentNodeLengthFromRoot--;
            }
        }
        
        return (max - min) <= 1;
    }

    // tests

   @Test
    public void fullTreeTest() {
        final BinaryTreeNode root = new BinaryTreeNode(5);
        final BinaryTreeNode a = root.insertLeft(8);
        final BinaryTreeNode b = root.insertRight(6);
        a.insertLeft(1);
        a.insertRight(2);
        b.insertLeft(3);
        b.insertRight(4);
        final boolean result = isBalanced(root);
        assertTrue(result);
    }

    @Test
    public void bothLeavesAtTheSameDepthTest() {
        final BinaryTreeNode root = new BinaryTreeNode(3);
        root.insertLeft(4).insertLeft(1);
        root.insertRight(2).insertRight(9);
        final boolean result = isBalanced(root);
        assertTrue(result);
    }

    @Test
    public void leafHeightsDifferByOneTest() {
        final BinaryTreeNode root = new BinaryTreeNode(6);
        root.insertLeft(1);
        root.insertRight(0).insertRight(7);
        final boolean result = isBalanced(root);
        assertTrue(result);
    }

    @Test
    public void leafHeightsDifferByTwoTest() {
        final BinaryTreeNode root = new BinaryTreeNode(6);
        root.insertLeft(1);
        root.insertRight(0).insertRight(7).insertRight(8);
        final boolean result = isBalanced(root);
        assertFalse(result);
    }

    @Test
    public void bothSubTreesSuperbalancedTest() {
        final BinaryTreeNode root = new BinaryTreeNode(1);
        root.insertLeft(5);
        final BinaryTreeNode b = root.insertRight(9);
        b.insertLeft(8).insertLeft(7);
        b.insertRight(5);
        final boolean result = isBalanced(root);
        assertFalse(result);
    }

    @Test
    public void bothSubTreesSuperbalancedTwoTest() {
        final BinaryTreeNode root = new BinaryTreeNode(1);
        final BinaryTreeNode a = root.insertLeft(2);
        a.insertLeft(3);
        a.insertRight(7).insertRight(8);
        root.insertRight(4).insertRight(5).insertRight(6).insertRight(9);
        final boolean result = isBalanced(root);
        assertFalse(result);
    }

    @Test
    public void onlyOneNodeTest() {
        final BinaryTreeNode root = new BinaryTreeNode(1);
        final boolean result = isBalanced(root);
        assertTrue(result);
    }

    @Test
    public void treeIsLinkedListTest() {
        final BinaryTreeNode root = new BinaryTreeNode(1);
        root.insertRight(2).insertRight(3).insertRight(4);
        final boolean result = isBalanced(root);
        assertTrue(result);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(CheckBalancedTree.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}