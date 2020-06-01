package algorithms; 

/**
Given a binary tree, return an array containing nodes in its right view. The right view of a binary tree is the set of nodes visible when the tree is seen from the right side.
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

class RightViewTree {
  public static List<TreeNode> traverse(TreeNode root) {
    List<TreeNode> result = new ArrayList<>();
    
    Deque<TreeNode> queue = new ArrayDeque<>(); 
    queue.offer(root); 

    while (!queue.isEmpty()) {

      int levelSize = queue.size();

      for (int i = 0; i < levelSize; i++) {
        TreeNode currentNode = queue.poll();
        if (i == levelSize - 1) {
          // add the last node of every level 
          result.add(currentNode);
        }

        if (currentNode.left != null) {
          queue.offer(currentNode.left);
        }

        if (currentNode.right != null) {
          queue.offer(currentNode.right);
        }
      }
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
    root.left.left.left = new TreeNode(3);
    List<TreeNode> result = RightViewTree.traverse(root);
    for (TreeNode node : result) {
      System.out.print(node.val + " ");
    }
  }
}

