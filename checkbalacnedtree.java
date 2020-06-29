/**
Write a method to see if a binary tree ↴ is "superbalanced" (a new tree property we just made up).

A tree is "superbalanced" if the difference between the depths of any two leaf nodes ↴ is no greater than one.

Here's a sample binary tree node class:

RunTime -> O(N) 
Space ->    O(N) -> worstcase. For a linkedlist like structure. 
            O(LogN) -> Avg case, for a tree structue 

The basic idea is simply, if we have three ditinct integers representing the depth of a leaf node at any point in time, will be taken into the list. 
- For a balaned tree the highest difference in depth mustn't be greater than 1 
- If the list has mor ethan two distinct integers, it means we have a non-balacned tree.  [1, 2, 3]  -> 3 -1 = 2 ( > 1 )
- if the tree has a length of 2 integers and the absolute difference between this two digit is > 1 we have a non-balanced tree  [1, 4] -> 4 -1 = 3 (> 1)
 */
 
package algorithms;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.ArrayList; 
import java.util.List;

import static org.junit.Assert.*;

public class CheckBalancedTree {
    
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
    
    public static class BinaryTreeNodePair {
        
        public BinaryTreeNode node; 
        public int depth;
        
        public BinaryTreeNodePair(BinaryTreeNode n, int depth) {
            this.node = n; 
            this.depth = depth; 
        }
    }
    
    public static boolean isBalanced(BinaryTreeNode treeRoot) {
        
        if (treeRoot == null) {
            return true;
        }
        
        Deque<BinaryTreeNodePair> nodes = new ArrayDeque<>();
        List<Integer> depths = new ArrayList<>(3);
        
        nodes.push(new BinaryTreeNodePair(treeRoot, 0));
        
        while (! nodes.isEmpty()) {
            
            BinaryTreeNodePair currentNodePair = nodes.pop();
            
            BinaryTreeNode currentNode = currentNodePair.node;
            int depth = currentNodePair.depth; 
            
            if (currentNode.left == null && currentNode.right == null) {
                
                // We found a leaf 
                //check if leaf depth is already in list, if not add. 
                
                if (!depths.contains(depth)) {
                    depths.add(depth);
                    
                    if (depths.size() > 2 || (depths.size() == 2 && Math.abs(depths.get(0) - depths.get(1)) > 1)) {
                        return false; 
                    }
                }
                
                //Since we are checking to see if no depth difference is greater than 1
                //This is also means once we have a list of depths with three distinct values, it can return false OR
                // If the difference betwen any two valu is more than 1 we return false. 
               
            } else {
                if (currentNode.left != null) {
                    nodes.push(new BinaryTreeNodePair(currentNode.left, depth + 1));
                }
                
                 if (currentNode.right != null) {
                    nodes.push(new BinaryTreeNodePair(currentNode.right, depth + 1));
                }
            }
        }
        
        return true; 
    }
    
    // My Solution... 
    // Brute force 
    // memory intensive 
    /*public static class BinaryTreeNode {

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
                  
                if (currentNode.right == null && currentNode.left == null) {                    //LeafNode 
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
                    
                    if ((max - min) > 1) return false;  //ShortCircuting..
                    
                }
                BinaryTreeNode tempNode = currentNode;
                currentNode = tempNode.parentNode;
                currentNodeLengthFromRoot--;
            }
        }
        
        return (max - min) <= 1;
    }*/

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