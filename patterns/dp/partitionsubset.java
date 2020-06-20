package algorithms; 

/**
Given a set of positive numbers, find if we can partition it into two subsets such that the sum of elements in both subsets is equal.

Example 1:

Input: {1, 2, 3, 4}
Output: True
Explanation: The given set can be partitioned into two subsets with equal sum: {1, 4} & {2, 3}

 */
class PartitionSet {
 
   //Bottom-up 
   // Time & Space o(N*S) 
  public boolean canPartition(int[] num) {
    int n = num.length;
    // find the total sum
    int sum = 0;
    for (int i = 0; i < n; i++)
      sum += num[i];

    // if 'sum' is a an odd number, we can't have two subsets with same total
    if(sum % 2 != 0)
      return false;

    // we are trying to find a subset of given numbers that has a total sum of ‘sum/2’.
    sum /= 2;

    boolean[][] dp = new boolean[n][sum + 1];

    // populate the sum=0 columns, as we can always for '0' sum with an empty set
    for(int i=0; i < n; i++)
      dp[i][0] = true;

    // with only one number, we can form a subset only when the required sum is equal to its value
    for(int s=1; s <= sum ; s++) {
      dp[0][s] = (num[0] == s ? true : false);
    }

    // process all subsets for all sums
    for(int i=1; i < n; i++) {
      for(int s=1; s <= sum; s++) {
        // if we can get the sum 's' without the number at index 'i'
        if(dp[i-1][s]) {
          dp[i][s] = dp[i-1][s];
        } else if (s >= num[i]) { // else if we can find a subset to get the remaining sum
          dp[i][s] = dp[i-1][s-num[i]];
        }
      }
    }

    // the bottom-right corner will have our answer.
    return dp[n-1][sum];
  }

 //+++++++++++++++++++++++++++++++++++++++++++++ R E C U R S I V E ++++++++++++++++++++++++++++++++++++++++++++++++++
  //Time -> O(S^N) 
  // Space -> O(N) stack space 
 static boolean canPartition(int[] num) {
    int sum = 0; 
    for (int a : num) {
      sum += a;
    }

    if (sum % 2 != 0) return false; // can't be paired.  

    return canPartitionRecursive(num, sum % 2, 0);
  }

  // Recursive | Bruteforce O(2^N) 
  // Space -> O(N) -> Call Stack 
  private static boolean canPartitionRecursive(int[] num, int sum, int currentIndex) {

    if (sum == 0) return true; 

    if (currentIndex > num.length - 1) return false; 

    if (num[currentIndex] <= sum) {
      if (canPartitionRecursive(num, sum - num[currentIndex], currentIndex + 1)) {
        return true; 
      }
    }

    return canPartitionRecursive(num, sum, currentIndex + 1);
  }

  public static void main(String[] args) {
    PartitionSet ps = new PartitionSet();
    int[] num = {1, 2, 3, 4};
    System.out.println(ps.canPartition(num));
    num = new int[]{1, 1, 3, 4, 7};
    System.out.println(ps.canPartition(num));
    num = new int[]{2, 3, 4, 6};
    System.out.println(ps.canPartition(num));
  }
}
