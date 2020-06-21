package algorithms; 

/**
Given a set of positive numbers, find the total number of subsets whose sum is equal to a given number ‘S’.

Example 1: #
Input: {1, 1, 2, 3}, S=4
Output: 3
The given set has '3' subsets whose sum is '4': {1, 1, 2}, {1, 3}, {1, 3}
Note that we have two similar sets {1, 3}, because we have two '1' in our input.

Runtime -> O(N8S) 
Space -> O(S) 
 */
 
class SubsetSum {
  
  static int countSubsets(int[] num, int sum) {
    int dp[] = new int[sum + 1]; 

    dp[0] = 1; 

    for (int i = 0; i < num.length; i++) {
      for (int s = sum; s >= 0; s--) {
        int count = 0; 
        if (num[i] <= s) {
          count += dp[s - num[i]];
        } 
        count += dp[s];
        dp[s] = count; 
      }
    }

    return dp[sum];
  }

  // T O P - D O W N -> O(N*S) -> O(N*S)
  /**static int countSubsets(int[] num, int sum) {
    Integer[][] dp = new Integer[num.length][sum + 1];
    return countSubsetsRecursive(dp, num, sum, 0);
  }

  private static int countSubsetsRecursive(Integer[][] dp, int[] num, int differeneSoFar, int currentIndex) {

    if (differeneSoFar == 0) return 1; 

    if (currentIndex > num.length - 1) return 0;

    if (dp[currentIndex][differeneSoFar] == null) {
      int count = 0; 
      if (num[currentIndex] <= differeneSoFar) {
        count += countSubsetsRecursive(dp, num, differeneSoFar - num[currentIndex], currentIndex + 1);
      }

      count += countSubsetsRecursive(dp, num, differeneSoFar, currentIndex + 1);

      dp[currentIndex][differeneSoFar] = count; 
    }
    
    return dp[currentIndex][differeneSoFar];
  } **/

  // R E C U R S I V E -> Brute Force -> O(2^N) -> O(N) 
  /** static int countSubsets(int[] num, int sum) {
    return countSubsetsRecursive(num, sum, 0);
  }

  private static int countSubsetsRecursive(int[] num, int differeneSoFar, int currentIndex) {

    if (differeneSoFar == 0) return 1; 

    if (currentIndex > num.length - 1) return 0;

    int count = 0; 
    if (num[currentIndex] <= differeneSoFar) {
      count += countSubsetsRecursive(num, differeneSoFar - num[currentIndex], currentIndex + 1);
    }

    count += countSubsetsRecursive(num, differeneSoFar, currentIndex + 1);

    return count; 
  } **/

  public static void main(String[] args) {
    SubsetSum ss = new SubsetSum();
    int[] num = { 2, 1, 2, 3, 1};
    System.out.println(ss.countSubsets(num, 4));
    num = new int[] { 1, 2, 7, 1, 5 };
    System.out.println(ss.countSubsets(num, 9));
  }
}
