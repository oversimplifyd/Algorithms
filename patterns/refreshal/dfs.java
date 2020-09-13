import java.util.*; 

class DFS {

    public static boolean binaryTreePathSum(TreeNode root, int sum)
    {
        /**
         * Given a binary tree and a number ‘S’, find if the tree has a path from root-to-leaf such that the sum of all the node values of that path equals ‘S’.
         * 
         *  10
         *              1
         *        3            2
         *    4       5     7       6
         * 
         *   stack -> 1
         *   pop  ->  10 - 1 
         *   stack -> 2 3 
         *   POP -> 3 -> 9 - 3 
         *   stack -> 2 6 7 
         *   POP -> 7 -> 9 - 7 -> 2 | replace it 
         *   POP -> 6 -> 9 - 0 
         *   
         *   1 2 4 
         *   1 2 5 
         *   1 3 6 
         *   1 3 7 
         * 
         *   
         */    

         Deque<TreeNodeWithAccrual> stack = new ArrayDeque<>(); 
         stack.add(new TreeNodeWithAccrual(root, sum));

         while (!stack.isEmpty()) {

            TreeNodeWithAccrual current = stack.pop();

             TreeNode currentNode = current.node; 

             int currentSum = current.accruedValue - currentNode.value; 

             if (currentSum == 0) return true;  

             if (currentNode.left != null) {
                 stack.add(new TreeNodeWithAccrual(currentNode.left, currentSum));
             }

             if (currentNode.right != null) {
                 stack.add(new TreeNodeWithAccrual(currentNode.right, currentSum));
             }
         }

         return false; 
    }

    public static boolean binaryTreePathSumRecursion(TreeNode root, int sum) {

        if (root == null) return false; 

        if (sum - root.value == 0) return true; 

        return binaryTreePathSumRecursion(root.left, sum - root.value) 
        || binaryTreePathSumRecursion(root.right, sum - root.value);
    }

    public static List<List<Integer>> allPathWithGivenSum(TreeNode root, int sum) {
        /**
         * Given a binary tree and a number ‘S’, find all paths from root-to-leaf such that the sum of all the node values of each path equals ‘S’.
         * 
         *             1
         *        3            2
         *    4       6    7       8
         *  O ( n x maximumDepth) 
         *  O(totalPath x numberOfItemsInMaximumPathDepth)
         * O (paths x depth) 
         */

         Deque<TreeNodeWithAccrualWithPath> stack = new ArrayDeque<>();

         stack.add(new TreeNodeWithAccrualWithPath(root, null, sum));

         List<List<Integer>> path = new ArrayList<>();

         while (!stack.isEmpty()) {

            TreeNodeWithAccrualWithPath current = stack.pop();

            TreeNode currentNode = current.node; 

            int currentSum = current.accruedValue - currentNode.value;;

            if (currentSum == 0) {
                path.add(reconstructPath(current));
            } else {
                if (currentNode.left != null) stack.add(new TreeNodeWithAccrualWithPath(currentNode.left, current, currentSum));
                if (currentNode.right != null) stack.add(new TreeNodeWithAccrualWithPath(currentNode.right, current, currentSum));
            }
         }

         return path; 
    }

    public static List<List<Integer>> allPathWithGivenSumRecursive(TreeNode root, int sum) {
        /**
         * Given a binary tree and a number ‘S’, find all paths from root-to-leaf such that the sum of all the node values of each path equals ‘S’.
         * 
         *             1  -> [1], [......]
         *        3            2  -> [1,2], [......]
         *    4       6    7 [1,2,7], [......]      8
         *  O ( n x maximumDepth) 
         *  O(totalPath x numberOfItemsInMaximumPathDepth)
         * O (paths x depth) 
         */

         if (root == null) throw new IllegalArgumentException("Invalid params..");

         List<List<Integer>> totalPath = new ArrayList<>();

         pathWithRecursion(root, sum, new ArrayList<>(), totalPath);

         return totalPath;
    }

    public static void pathWithRecursion(TreeNode root, int sum, List<Integer> currentPath, List<List<Integer>> totalPaths) {

         currentPath.add(root.value);

        if (sum - root.value == 0 && root.left == null && root.right == null) {
            totalPaths.add(new ArrayList<>(currentPath));
        } else {
            if (root.left != null) pathWithRecursion(root.left, sum - root.value, currentPath, totalPaths);
            if (root.right != null) pathWithRecursion(root.right, sum - root.value, currentPath, totalPaths);
        }

        currentPath.remove(currentPath.size() - 1);
    }

    private static List<Integer> reconstructPath(TreeNodeWithAccrualWithPath current) {

        List<Integer> path = new ArrayList<>();

        while (current != null) {
            path.add(current.node.value);
            current = current.previousNode;
        }

        return path; 
    }


    public static void main(String[] args) {
        // TreeNode root = new TreeNode(12);
        // root.left = new TreeNode(7);
        // root.right = new TreeNode(1);
        // root.left.left = new TreeNode(9);
        // root.right.left = new TreeNode(10);
        // root.right.right = new TreeNode(5);
        // System.out.println("Tree has path: " + DFS.binaryTreePathSumRecursion(root, 23));
        // System.out.println("Tree has path: " + DFS.binaryTreePathSumRecursion(root, 16));

        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        int sum = 23;
        List<List<Integer>> result = DFS.allPathWithGivenSumRecursive(root, sum);
        System.out.println("Tree paths with sum " + sum + ": " + result);
    }
}

class TreeNode {

    int value; 
    TreeNode left; 
    TreeNode right; 

    public TreeNode(int value) {
        this.value = value; 
    }
}

class TreeNodeWithAccrual {
    TreeNode node; 
    int accruedValue; 

    public TreeNodeWithAccrual(TreeNode node, int accruedValue) {
        this.accruedValue = accruedValue;
        this.node = node; 
    }
}

class TreeNodeWithAccrualWithPath {
    TreeNode node; 
    int accruedValue; 

    TreeNodeWithAccrualWithPath previousNode; 

    public TreeNodeWithAccrualWithPath(TreeNode node, TreeNodeWithAccrualWithPath previous, int accruedValue) {
        this.accruedValue = accruedValue;
        this.node = node; 
        this.previousNode = previous;
    }
}
