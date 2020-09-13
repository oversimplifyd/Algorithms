import java.util.*; 

class BFS {

    public static List<List<Integer>> levelByLevelOrderTraversal(TreeNode root)
    {
        /***
         * Given a binary tree, populate an array to represent its level-by-level traversal. You should populate the values of all nodes of each level from left to right in separate sub-arrays.
         * 
         * BFS is typically a Queue 
         * push the head into a queue 
         * While the queue is not empty 
         * pop the firstItem from queue 
         * if item has left, doLogic, push left into a queue 
         * if item ha a right, doLogic, push right into a queue 
         * else if item is a leaft dequeue and continue 
         * 
         * For level order traversals, we want to be able to get the total items in the queue at any point in time, to do a nsted travesal 
         */

         if (root == null) throw new IllegalArgumentException("Incorrect input");

         Deque<TreeNode> queue = new ArrayDeque<>(); 
         List<List<Integer>> result = new ArrayList<>();

         queue.add(root); 

         /**
          *                 5
                    4              3
                2       1       0       4
          */
         while (!queue.isEmpty()) {

             int levelCount = queue.size();
             List<Integer> levelResult = new ArrayList<>();
                          
             for (int i = 0; i < levelCount; i++) {
                 TreeNode currentNode = queue.poll();

                 levelResult.add(currentNode.value);

                 if (currentNode.left != null) {
                     queue.add(currentNode.left);
                 }

                 if (currentNode.right != null) {
                     queue.add(currentNode.right);
                 }
             }

             if (levelResult.size() > 0) {
                result.add(levelResult);
             }
         }

         return result;
    }

    public static List<List<Integer>> zizagLevelTraversal(TreeNode root) {
        /**
         * Given a binary tree, populate an array to represent its zigzag level order traversal. You should populate the values of all nodes of the first level from left to right, then right to left for the next level and keep alternating in the same manner for the following levels.
         */

         /**
          *                12
                    7               1
                9               10       5 

                12          12
                7  1        1 7 
                9 10 5      9 10 5
          */

         Deque<TreeNode> queue = new ArrayDeque<>();
         List<List<Integer>> result = new ArrayList<>(); 

         boolean leftToRight = true; 

         queue.offer(root);

         while (!queue.isEmpty()) {
             int levelSize = queue.size();
             List<Integer> levelResult = new LinkedList<>();

             for (int i = 0; i < levelSize; i++) {
                 TreeNode currentNode = queue.poll();

                if (leftToRight) {
                    levelResult.add(currentNode.value);
                } else {
                    levelResult.add(0, currentNode.value);
                }

                 if (currentNode.left != null) queue.offer(currentNode.left);
                 if (currentNode.right != null) queue.offer(currentNode.right);
             }

             leftToRight = !leftToRight;

             if (levelResult.size() > 0) result.add(levelResult);
         }

         return result; 
    }
    
    public static void main(String[] args) {
        // TreeNode root = new TreeNode(12);
        // root.left = new TreeNode(7);
        // root.right = new TreeNode(1);
        // root.left.left = new TreeNode(9);
        // root.right.left = new TreeNode(10);
        // root.right.right = new TreeNode(5);
        // List<List<Integer>> result = BFS.levelByLevelOrderTraversal(root);
        // System.out.println("Level order traversal: " + result);

        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        root.right.left.left = new TreeNode(20);
        root.right.left.right = new TreeNode(17);

        List<List<Integer>> result = BFS.zizagLevelTraversal(root);
        System.out.println("Level order traversal: " + result);
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
