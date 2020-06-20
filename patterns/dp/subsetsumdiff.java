package algorithms; 

/**
Given a set of positive numbers, partition the set into two subsets with minimum difference between their subset sums.

Example 1:
Input: {1, 2, 3, 9}
Output: 3
Explanation: We can partition the given set into two subsets where minimum absolute difference 
between the sum of numbers is '3'. Following are the two subsets: {1, 2, 3} & {9}.
 */
 
class SubSetSumDifference {

// Recursive  With Memoization (Top - Down)
// O(N*S)
// O(N*S) 
  public static int minDiff(int[] num) {
       int total = 0; 
      for (int number: num) {
          total += number; 
      }

      Integer[][] dp = new Integer[num.length][total + 1];
      return minDiffRecursive(dp, num, total, total, 0);
  }
  
  private static int minDiffRecursive(Integer[][] dp, int[] num, int sum, int currentTotal, int currentIndex) {

      if (currentIndex > num.length - 1) {
          int otherPair = sum - currentTotal; 
          return Math.abs(currentTotal - otherPair);
      }

      if (dp[currentIndex][currentTotal] == null) {
        int minIncludingInPair = Integer.MAX_VALUE;
        if (num[currentIndex] <= currentTotal) {
            minIncludingInPair = minDiffRecursive(dp, num, sum, currentTotal - num[currentIndex], currentIndex + 1);
        }

        int minExclusingInPair = minDiffRecursive(dp, num, sum, currentTotal, currentIndex+1);

        dp[currentIndex][currentTotal] = Math.min(minIncludingInPair, minExclusingInPair);
      }

      return dp[currentIndex][currentTotal];
  }

// // Recursive  -> Brute Force 
// // O(2^N) 
// // O(N) 
//   public static int minDiff(int[] num) {
//        int total = 0; 
//       for (int number: num) {
//           total += number; 
//       }
      
//       return minDiffRecursive(num, total, total, 0);
//   }

//   private static int minDiffRecursive(int[] num, int sum, int currentTotal, int currentIndex) {

//       if (currentIndex > num.length - 1) {
//           int otherPair = sum - currentTotal; 
//           return Math.abs(currentTotal - otherPair);
//       }

//       int minIncludingInPair = Integer.MAX_VALUE;
//       if (num[currentIndex] <= currentTotal) {
//           minIncludingInPair = minDiffRecursive(num, sum, currentTotal - num[currentIndex], currentIndex + 1);
//       }

//       int minExclusingInPair = minDiffRecursive(num, sum, currentTotal, currentIndex+1);

//       return Math.min(minIncludingInPair, minExclusingInPair);
//   }

  public static void main(String[] args) {
    SubSetSumDifference ps = new SubSetSumDifference();
    int[] num = {1, 3, 100, 4};
    System.out.println(ps.minDiff(num));
  }
}
