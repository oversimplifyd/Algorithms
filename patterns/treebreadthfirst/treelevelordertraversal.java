package algorithms; 

/**
Given a binary tree, populate an array to represent its level-by-level traversal. You should populate the values of all nodes of each level from left to right in separate sub-arrays
Runtime -> O(N) where N is the total number of nodes. 
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

class LevelOrderTraversal {
  public static List<List<Integer>> traverse(TreeNode root) {

    if (root == null) {
      return null; 
    }

    List<List<Integer>> result = new ArrayList<List<Integer>>();
    Deque<TreeNode> queue = new ArrayDeque<TreeNode>(); 

    queue.offer(root); 

    while (!queue.isEmpty()) {
      int levelSize = queue.size(); 

      List<Integer> levelList = new ArrayList<Integer>(levelSize);

      for (int i = 0; i < levelSize; i++) {
        TreeNode currentNode = queue.poll();
        levelList.add(currentNode.val);

        if (currentNode.left != null) {
          queue.offer(currentNode.left);
        }

        if (currentNode.right != null) {
          queue.offer(currentNode.right);
        }
      }

      result.add(levelList);
      
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
    List<List<Integer>> result = LevelOrderTraversal.traverse(root);
    System.out.println("Level order traversal: " + result);
  }
}
