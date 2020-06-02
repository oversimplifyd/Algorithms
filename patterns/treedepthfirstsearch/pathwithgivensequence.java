package algorithms; 

/**
Given a binary tree and a number sequence, find if the sequence is present as a root-to-leaf path in the given tree.

Path with given sequence
O(N) 
O(N) 
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

class PathWithGivenSequence {
  public static boolean findPath(TreeNode root, int[] sequence) {
    if (root == null) {
      return false; 
    }
    
    return findPathRecursive(root, sequence, 0, false);
  }

  private static boolean findPathRecursive(TreeNode root, int[] sequence, int currentIndex, boolean matching) {

    if (root == null) {
      return false; 
    }

    if (currentIndex > sequence.length - 1) {
      return false; 
    }

   if (root.left == null && root.right == null) {
      if (matching && sequence[currentIndex] == root.val && currentIndex == sequence.length - 1)
       return true; 
    }
    
    if (sequence[currentIndex] == root.val) {
      matching = true; 
    } else {
      matching = false; 
    }

    currentIndex = currentIndex + 1; 

    return findPathRecursive(root.left, sequence, currentIndex, matching) || findPathRecursive(root.right, sequence, currentIndex, matching);
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(0);
    root.right = new TreeNode(1);
    root.left.left = new TreeNode(1);
    root.right.left = new TreeNode(6);
    root.right.right = new TreeNode(5);

    System.out.println("Tree has path sequence: " + PathWithGivenSequence.findPath(root, new int[] { 1, 0, 7 }));
    System.out.println("Tree has path sequence: " + PathWithGivenSequence.findPath(root, new int[] { 1, 1, 6 }));
        System.out.println("Tree has path sequence: " + PathWithGivenSequence.findPath(root, new int[] { 1, 0, 1 }));
            System.out.println("Tree has path sequence: " + PathWithGivenSequence.findPath(root, new int[] { 1, 0, 5 }));

  }
}
