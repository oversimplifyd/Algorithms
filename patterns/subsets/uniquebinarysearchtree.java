/**
Given a number ‘n’, write a function to return all structurally unique Binary Search Trees (BST) that can store values 1 to ‘n’?
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

class UniqueTrees {
  public static List<TreeNode> findUniqueTrees(int n) {    
    if (n <= 0) 
      return null; 
    return findUniqueRecursive(1, n);
  }

  private static List<TreeNode> findUniqueRecursive(int start, int end) {

    List<TreeNode> result = new ArrayList<TreeNode>();
    
    if (start > end) {
      // no left subtree for the current root 
      result.add(null);
      return result;
    }

    for (int i = start; i <= end; i++) {

      List<TreeNode> leftSubTree = findUniqueRecursive(0, i - 1);
      List<TreeNode> rightSubTree = findUniqueRecursive(i+1, end);

      for (TreeNode left: leftSubTree) {
        for (TreeNode right: rightSubTree) {
          TreeNode root = new TreeNode(i);
          root.left = left; 
          root.right = right; 
          result.add(root);
        }
      }
    }

    return result; 
  }
  public static void main(String[] args) {
    List<TreeNode> result = UniqueTrees.findUniqueTrees(2);
    System.out.print("Total trees: " + result.size());
  }
}
