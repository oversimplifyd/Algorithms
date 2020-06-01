package algorithms; 

/**
Given a binary tree, connect each node with its level order successor. The last node of each level should point to the first node of the next level.
 */
import java.util.*;

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;
  TreeNode next;

  TreeNode(int x) {
    val = x;
    left = right = next = null;
  }
};

class ConnectAllSiblings {
  public static void connect(TreeNode root) {
    if (root == null) {
      throw new IllegalArgumentException("Invalid");
    }

    Deque<TreeNode> queue = new ArrayDeque<>();
    queue.offer(root); 
    TreeNode previousNode = null; 

    while (!queue.isEmpty()) {
      int levelSize = queue.size();

      for (int i = 0; i < levelSize; i++) {
        TreeNode currentNode = queue.poll(); 
        if (previousNode != null) {
          previousNode.next = currentNode; 
        }
        previousNode = currentNode; 

        if (currentNode.left != null) {
          queue.offer(currentNode.left); 
        }

        if (currentNode.right != null) {
          queue.offer(currentNode.right);
        }
      }

    }
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(12);
    root.left = new TreeNode(7);
    root.right = new TreeNode(1);
    root.left.left = new TreeNode(9);
    root.right.left = new TreeNode(10);
    root.right.right = new TreeNode(5);
    ConnectAllSiblings.connect(root);

    // level order traversal using 'next' pointer
    TreeNode current = root;
    System.out.println("Traversal using 'next' pointer: ");
    while (current != null) {
      System.out.print(current.val + " ");
      current = current.next;
    }
  }
}