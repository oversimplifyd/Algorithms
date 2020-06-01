package algorithms; 

/**
Given a binary tree, populate an array to represent its zigzag level order traversal. You should populate the values of all nodes of the first level from left to right, then right to left for the next level and keep alternating in the same manner for the following levels.
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

class ZigzagTraversal {
  public static List<List<Integer>> traverse(TreeNode root) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();

    if (root == null) {
      throw new IllegalArgumentException("Invalid");
    }

    Deque<TreeNode> queue = new ArrayDeque<>(); 
    queue.offer(root);
    boolean leftToRight = true; 

    while (!queue.isEmpty()){

      int levelSize = queue.size(); 

      List<Integer> currentList = new LinkedList<>(); 

       // Initial direction 

      for (int i = 0; i < levelSize; i++) {
        TreeNode currentNode = queue.poll();

        // Insert based on current direction 
        // LinkedList provides a way to insert either at the beginning or the end 
        // prepend, append 
        if (leftToRight) {
          //append
          currentList.add(currentNode.val);
        } else {
          // prepend 
          currentList.add(0, currentNode.val);
        }

        if (currentNode.left != null) {
          queue.offer(currentNode.left);
        }

        if (currentNode.right != null) {
          queue.offer(currentNode.right);
        }
      }

      result.add(currentList);
      leftToRight = !leftToRight; // change the direction 
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
    root.right.left.left = new TreeNode(20);
    root.right.left.right = new TreeNode(17);
    List<List<Integer>> result = ZigzagTraversal.traverse(root);
    System.out.println("Zigzag traversal: " + result);
  }
}
