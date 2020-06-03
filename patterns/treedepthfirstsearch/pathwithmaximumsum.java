package algorithms; 

/**
Find the path with the maximum sum in a given binary tree. Write a function that returns the maximum sum. A path can be defined as a sequence of nodes between any two nodes and doesn’t necessarily pass through the root.

 */

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
};

class MaximumPathSum {

  // taking the maximum at any valid point in time 
  // similar to the tree diameter problem 
  static int globalMaxSum = Integer.MIN_VALUE;

  public static int findMaximumPathSum(TreeNode root) {
    findMaximumPathSumRecursive(root);
    return globalMaxSum;
  }

  private static int findMaximumPathSumRecursive(TreeNode root) {

    if (root == null){
      return 0; 
    }

    int maxPathSumLeft = findMaximumPathSumRecursive(root.left);
    int maxPathSumRight = findMaximumPathSumRecursive(root.right);

    // include the current root as part of sum at every point in time 
    int localSum = maxPathSumLeft + maxPathSumRight + root.val;

    // Take the maxSumSeenSoFAr 
    globalMaxSum = Math.max(globalMaxSum, localSum);

    return Math.max(maxPathSumLeft, maxPathSumRight) + root.val; 
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    System.out.println("Maximum Path Sum: " + MaximumPathSum.findMaximumPathSum(root));
    
    root.left.left = new TreeNode(1);
    root.left.right = new TreeNode(3);
    root.right.left = new TreeNode(5);
    root.right.right = new TreeNode(6);
    root.right.left.left = new TreeNode(7);
    root.right.left.right = new TreeNode(8);
    root.right.right.left = new TreeNode(9);
    System.out.println("Maximum Path Sum: " + MaximumPathSum.findMaximumPathSum(root));
    
    root = new TreeNode(-1);
    root.left = new TreeNode(-3);
    System.out.println("Maximum Path Sum: " + MaximumPathSum.findMaximumPathSum(root));
  }
}
