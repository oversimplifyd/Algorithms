// All Paths to a given sum 
// Runtime O(NLogN) 
// Where N is the total number of paths we are stroing and logn is the time required to copy nodes for a new path 
// 
import java.util.*;

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
};

class FindAllTreePaths {
  public static List<List<Integer>> findPaths(TreeNode root, int sum) {
    List<List<Integer>> allPaths = new ArrayList<>();
    
    if (root == null) {
      return allPaths; 
    }
    
    findAllPaths(root, new ArrayList<Integer>(), sum, allPaths);

    return allPaths; 
  }

  private static void findAllPaths(TreeNode root, List<Integer> seenPaths, int sum, List<List<Integer>> allPaths) {

    seenPaths.add(root.val); 

    if (root.val == sum && root.left == null && root.right == null) {
        // Copy operation 
        //There can only be a maximum of logN nodes atthis time 
      allPaths.add(new ArrayList<Integer>(seenPaths));
    } else {
      if (root.left != null) {
        findAllPaths(root.left, seenPaths, sum - root.val, allPaths);
      }

      if (root.right != null) {
        findAllPaths(root.right, seenPaths, sum - root.val, allPaths);
      }
    }

    seenPaths.remove(seenPaths.size() - 1); // backtracking 
  }
  
  public static void main(String[] args) {
    TreeNode root = new TreeNode(12);
    root.left = new TreeNode(7);
    root.right = new TreeNode(1);
    root.left.left = new TreeNode(4);
    root.right.left = new TreeNode(10);
    root.right.right = new TreeNode(5);
    int sum = 23;
    List<List<Integer>> result = FindAllTreePaths.findPaths(root, sum);
    System.out.println("Tree paths with sum " + sum + ": " + result);
  }
}
