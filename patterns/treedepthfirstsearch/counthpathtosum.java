/**
Given a binary tree and a number ‘S’, find all paths in the tree such that the sum of all the node values of each path equals ‘S’. Please note that the paths can start or end at any node but all paths must follow direction from parent to child (top to bottom).

If we are keeping track of the paths, we always need to backtrack at the leaf node to reuse the list for the alternate nodes 

Runtime -> O(N^2) worst case 
Runtime -> O(NLogN0 best case, balanced tree 
Space -> O(N))
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

class CountAllPathSum {
  public static int countPaths(TreeNode root, int S) {
    List<Integer> currentPath = new ArrayList<Integer>(); 
    return countPathsRecursive(root, S, currentPath);
  }

  private static int countPathsRecursive(TreeNode root, int S, List<Integer> currentPath) {

    if (root == null) {
      return 0; 
    }

    currentPath.add(root.val);
    ListIterator<Integer> iterator = currentPath.listIterator(currentPath.size());

    int pathSum = 0;
    int pathCount = 0; 
    while (iterator.hasPrevious()) {
      pathSum += iterator.previous(); 

      if (pathSum == S) {
        pathCount++; 
      }
    }

    pathCount += countPathsRecursive(root.left, S, currentPath);
    pathCount += countPathsRecursive(root.right, S, currentPath);

    // Backtrack, once we get to a leaf node, in order to re use the list for the alternate node
    // by removing the current leaf node 

    currentPath.remove(currentPath.size() -1);

    return pathCount; 
  }
  
  public static void main(String[] args) {
    TreeNode root = new TreeNode(12);
    root.left = new TreeNode(7);
    root.right = new TreeNode(1);
    root.left.left = new TreeNode(4);
    root.right.left = new TreeNode(10);
    root.right.right = new TreeNode(5);
    System.out.println("Tree has path: " + CountAllPathSum.countPaths(root, 11));
  }
}
