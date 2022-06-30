import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

import java.util.ArrayDeque;
import java.util.Deque; 

public class ValidBST {

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
    
    public static class BinaryTreeNodeWithBounds {
        
        public BinaryTreeNode node; 
        public int upperBound; 
        public int lowerBound;
        
        public BinaryTreeNodeWithBounds(BinaryTreeNode node, int lowerBound, int upperBound) {
            this.node = node;  
            this.upperBound = upperBound;
            this.lowerBound = lowerBound;
        }
    }
    
    public static boolean isBinarySearchTree(BinaryTreeNode root) {

        // determine if the tree is a valid binary search tree
        
        //Edge Case 1: 
        if (root == null) {
            return true; 
        }
        
        Deque<BinaryTreeNodeWithBounds> nodes = new ArrayDeque<>();
        
        nodes.push(new BinaryTreeNodeWithBounds(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
        
        while (! nodes.isEmpty()) {
            
            BinaryTreeNodeWithBounds nWithBound = nodes.pop();
            BinaryTreeNode currentNode = nWithBound.node;
            int upperBound = nWithBound.upperBound;
            int lowerBound = nWithBound.lowerBound;
            
            if (currentNode.value >= upperBound || currentNode.value <= lowerBound) {
                return false;
            }
            
            // Greedily pass the aceptable range for right and left nodes given the value of current node 
            // Right -> Node > currentNode Value (LowerBound) But less than currentNode upperBound. 
            // Left -> Node < currentNode Value (UpperBound) But greater than currentNode lowerBound 
            if (currentNode.left != null) {
                
                nodes.push(new BinaryTreeNodeWithBounds(currentNode.left, lowerBound, currentNode.value));
            }
            
            if (currentNode.right != null) {
                nodes.push(new BinaryTreeNodeWithBounds(currentNode.right, currentNode.value, upperBound));
            }
        }
        
        
        // Doesn't Cover all edge cases; 
        // Poor. 
        /*nodes.push(root);
        
        while (! nodes.isEmpty()) {
            BinaryTreeNode currentNode = nodes.pop();
            
            if (currentNode.left != null) {
                
                //Edge Case: 2 
                // Subtree is balanced but one of subtrees  left or right while true for subtree is false for current tree. 
                //This uses the ancestors approach. 
                if (currentNode.left.value >= currentNode.value || (currentNode.left.right != null && currentNode.left.right.value >= currentNode.value)) {
                    return false;
                }
                
                nodes.push(currentNode.left);
            }
            
            if (currentNode.right != null) {
                if (currentNode.right.value <= currentNode.value || (currentNode.right.left != null && currentNode.right.left.value <= currentNode.value)) {
                    return false;
                }
                
                nodes.push(currentNode.right);
            }
            
        }*/
        
        return true;
    }
    
    // Recursive Approach 
    // vulnerable to Stack Overflow prlblem. 
    
    public static boolean isBinarySearchTreeRecursive(BinaryTreeNode root) {
        
        return isBSTRecursive(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    public static boolean isBSTRecursive(BinaryTreeNode node, int lowerBound, int upperBound) {
        
        if (node == null) return true;
      
      if (node.value >= upperBound || node.value <= lowerBound) {
            return false;
        }
        
        return isBSTRecursive(node.left, lowerBound, node.value) && isBSTRecursive(node.right, node.value, upperBound);
    }
    
    // tests

    @Test
    public void validFullTreeTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        final BinaryTreeNode a = root.insertLeft(30);
        a.insertLeft(10);
        a.insertRight(40);
        final BinaryTreeNode b = root.insertRight(70);
        b.insertLeft(60);
        b.insertRight(80);
        final boolean result = isBinarySearchTreeRecursive(root);
        assertTrue(result);
    }

    @Test
    public void bothSubtreesValidTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        final BinaryTreeNode a = root.insertLeft(30);
        a.insertLeft(20);
        a.insertRight(60);
        final BinaryTreeNode b = root.insertRight(80);
        b.insertLeft(70);
        b.insertRight(90);
        final boolean result = isBinarySearchTreeRecursive(root);
        assertFalse(result);
    }

    @Test
    public void descendingLinkedListTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        root.insertLeft(40).insertLeft(30).insertLeft(20).insertLeft(10);
        final boolean result = isBinarySearchTreeRecursive(root);
        assertTrue(result);
    }

    @Test
    public void outOfOrderLinkedListTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        root.insertRight(70).insertRight(60).insertRight(80);
        final boolean result = isBinarySearchTreeRecursive(root);
        assertFalse(result);
    }

    @Test
    public void oneNodeTreeTest() {
        final BinaryTreeNode root = new BinaryTreeNode(50);
        final boolean result = isBinarySearchTreeRecursive(root);
        assertTrue(result);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ValidBST.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) { 
            System.out.println("All tests passed.");
        }
    }
}