import java.util.*; 

class DP {

    //TOPDOWN -> Bruteforce Approach 
    public static int boundedKnapsack(int[] weights, int[] profits, int capacity) {

        /**
         * Given the weights and profits of ‘N’ items, we are asked to put these items in a knapsack which has a capacity ‘C’. The goal is to get the maximum profit out of the items in the knapsack. Each item can only be selected once, as we don’t have multiple quantities of any item.

            Let’s take the example of Merry, who wants to carry some fruits in the knapsack to get maximum profit. Here are the weights and profits of the fruits:

            Items: { Apple, Orange, Banana, Melon }
                       A  B  C  D
            Weights: { 2, 3, 1, 4 }
            Profits: { 4, 5, 3, 7 }
            
            Knapsack capacity: 5

            firstProfit = select A, add the profit of A + to the recurisve solution of the remaining weights, provided the weight of A <= currentCapacity
            secondProfit = skip A, find the recursive solution of the rest of the weights 

            Math.max(firstProfit, secondProfit)
         */

         return boundedKnapsackRecursive(profits, weights, capacity, 0);
    }

    public static int boundedKnapsackRecursive(int[] profits, int[] weights, int capacity, int currentIndex) {

        //If the current recursive stack has capacity already reduced to 0 or 
        // doesn't have more profits to process, we have gotten to the end of the recursive call 
        if (capacity <= 0 || currentIndex > profits.length - 1) {
            return 0; 
        }

        int profit1 = 0; 

        if (weights[currentIndex] <= capacity) {
            // we can process the profit for this weight..
            // remove the weight of this index from the remaining capacity (Select Operation) since we have taken the profit already 
            // an item can only be selected once. 
            profit1 = profits[currentIndex] + boundedKnapsackRecursive(profits, weights, capacity - weights[currentIndex], currentIndex+1);
        }

        int profit2 = boundedKnapsackRecursive(profits, weights, capacity, currentIndex+1);

        return Math.max(profit1, profit2);
    }

    public static int boundedKnapsackButtomUp(int[] weights, int[] profits, int capacity) {

        // This involves Tabulation 
        // Given weights A -D, and a capacity, how can we break this down into optimal sub-structure, 
        // There is obviously an overalpping sub problem because we can make it work for 0, 1, 2...capacity 
        // Thus, we need to combine the solution of individual sub-problem 
        /**
         *  Take the profit of the weight subtracted from the capacity 
         * 
         *                                Capacity ->     
         *                                0     1       2       3       4       5       6
         *      weights     1             0     1       1       1       1       1       1
         *                  1,2           0     1       6       7       7       7       7
         *                  1,2,3         0     1       6       10      11      16      17
         *                  1,2,3,5       0     1       6       10      11      16     [17]
         */

         int profitLength = profits.length;
         int[][] dp = new int[profitLength][capacity + 1];

         for (int i =0; i < profitLength; i++) {
             // filles 0 capacity sub-problem with 0 values, 
             // since it is not possible to have profits with zero given capacity 
             dp[i][0] = 0; 
         }

         for (int i = 0; i <= capacity; i++) {
             //If we have only one weight and it is not more than the capacity 
             // We can take it 
             if (weights[0] <= i) {
                 dp[0][i] = profits[0];
             }
         }

         for (int runningWeight = 1; runningWeight < profitLength; runningWeight++) {
             for (int runningCapacity = 1; runningCapacity <= capacity; runningCapacity++) {

                // i the weight is not greater than the current capacity 
                 // Take the profit of the current weight + the profit of the remaining capacity - weiehgt 
                 // else use the weight of the previous solution

                 int profit1 = 0; 
                 int profit2 = 0; 
                if (weights[runningWeight] <= runningCapacity) {
                    profit1 = profits[runningWeight] + dp[runningWeight - 1][runningCapacity - weights[runningWeight]];
                }

                profit2 = dp[runningWeight-1][runningCapacity]; 

                dp[runningWeight][runningCapacity] = Math.max(profit1, profit2);
             }
         }

         return dp[profitLength-1][capacity];
    }

    public static int boundedKnapsackButtomUp2(int[] weights, int[] profits)
    {
        /**
         * 1
         * 1,2
         * 1,2,3
         * 1,2,3,4
         * 1,2,3,4,5
         * -----------------------
         * 0 1 2 3 4 5 6 7 8 
         * 0 0 0 0 0 0 0 0 0 
         * 0 1 1 1 1 1 1 1 1 
         *                 
         */
    }

    public static boolean subseSumPartition(int[] items)
    {
        /**
         * Given a set of positive numbers, find if we can partition it into two subsets such that the sum of elements in both subsets is equal.

            Example 1:

            Input: {1, 2, 3, 4}
            Output: True
            Explanation: The given set can be partitioned into two subsets with equal sum: {1, 4} & {2, 3}

            I essentially just want to check if any of these number groupings can give me a specific sum 
            When I include a number or whe I remoe it 
            I can only iclude an umber if the sum is >= that number 
         */
        
         int sum = 0; 
         for(int item: items) {
             sum += item; 
         }

         if (sum % 2 != 0) return false; //We can partition an odd sum into two equal subsets 

         sum /= 2; 

         return canPartitionRecursive(items, sum, 0);
    }

    private static boolean canPartitionRecursive(int[] items, int sum, int currentIndex) {
        if (sum == 0) return true; 

        if (currentIndex > items.length - 1) return false; 

        if (items[currentIndex] <= sum) {
            if (canPartitionRecursive(items, sum - items[currentIndex], currentIndex + 1)) {
                return true; 
            }
        }

        return canPartitionRecursive(items, sum, currentIndex + 1);
    }

    public static void main(String[] args){

        int[] profits = {1, 6, 10, 16};
        int[] weights = {1, 2, 3, 5};
        int maxProfit = DP.boundedKnapsackButtomUp(weights, profits, 7);
        System.out.println("Total knapsack profit ---> " + maxProfit);
        maxProfit = DP.boundedKnapsackButtomUp(weights, profits, 6);
        System.out.println("Total knapsack profit ---> " + maxProfit);
    }
}
