package algorithms; 

/**
Given a binary tree, populate an array to represent its level-by-level traversal in reverse order, i.e., the lowest level comes first. You should populate the values of all nodes in each level from left to right in separate sub-arrays.
The takeaway from this prolem is how we can add elements to a list using Linkedlist by amaking current element to insert stay at the top of the list unlike a regular arrayList.add() 
 */

import java.util.*;

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
};

class ReverseLevelOrderTraversal {
  public static List<List<Integer>> traverse(TreeNode root) {
    List<List<Integer>> result = new LinkedList<List<Integer>>();

    if (root == null) {
      throw new IllegalArgumentException("Invalid"); 
    }
    Deque<TreeNode> queue = new ArrayDeque<>(); 
    
    queue.offer(root); 

    while (!queue.isEmpty()) {
      int levelSize = queue.size(); 
      List<Integer> currentList = new ArrayList<>(levelSize); 

      for (int i =0; i < levelSize; i++) {
        TreeNode currentNode = queue.poll(); 

        currentList.add(currentNode.val); 

        if (currentNode.left != null) {
          queue.offer(currentNode.left);
        }

        if (currentNode.right != null) {
          queue.offer(currentNode.right); 
        }
      }

      // Linkedlist, when adding with this syntax. 
      // Makes sure the current stays at the beginning of the list 
      result.add(0, currentList); 
    }

    return result;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(12);
    root.left = new TreeNode(7);
    root.right = new TreeNode(1);
    root.left.left = new TreeNode(9);
    root.right.left = new TreeNode(10);
    root.right.right = new TreeNode(5);
    List<List<Integer>> result = ReverseLevelOrderTraversal.traverse(root);
    System.out.println("Reverse level order traversal: " + result);
  }
}
