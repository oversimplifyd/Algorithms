package algorithms; 

/**
Given a set of positive numbers, determine if a subset exists whose sum is equal to a given number ‘S’.

Example 1:
Input: {1, 2, 3, 7}, S=6
Output: True
The given set has a subset whose sum is '6': {1, 2, 3}
 */
 
class SubSetSum {

  //Bottom-up  
   // Time O(N*S)
   //Space O(S) 
  public boolean hasSum(int[] num, int S) {

      // include the result for a sum of zero (S+1) 
      boolean[] dp = new boolean[S+1];

      dp[0] = true; 

     for (int pairIndex = 1; pairIndex <= num.length; pairIndex++) {
         for (int currentSum = S; currentSum >= 0; currentSum--) {
             boolean res = false; 
             if (num[pairIndex-1] <= currentSum) {
                 res = dp[currentSum - num[pairIndex - 1]];
             }
              dp[currentSum] = res || dp[currentSum];
         }
         for (boolean e: dp) {
             System.out.print(e+"      ");
         }
         System.out.println("    ");
     }

     return dp[S];
  }

//    //Bottom-up 
//    // Time & Space o(N*S) 
//   public boolean hasSum(int[] num, int S) {

//       // include the result for a sum of zero (S+1) 
//       boolean[][] dp = new boolean[num.length+1][S+1];

//      // Populating the first columns 
//      for (int row = 0; row <= num.length; row++) {
//          dp[row][0] = true; 
//      }

//      // Populating the second columns 
//      for (int col = 1; col <= S; col++) {
//          dp[0][col] = false; 
//         //  if (num[0] < col) {
//         //      dp[0][col] = false; 
//         //  }
//      }

//      for (int row = 1; row <= num.length; row++) {
//          for (int col = 1; col <= S; col++) {
//              boolean res = false; 
//              if (num[row-1] <= col) {
//                  res = dp[row-1][col - num[row-1]];
//              }
//               dp[row][col] = res || dp[row-1][col];
//          }
//      }

//      return dp[num.length][S];
//   }

 //+++++++++++++++++++++++++++++++++++++++++++++ R E C U R S I V E ++++++++++++++++++++++++++++++++++++++++++++++++++
  //Time -> O(S^N) 
  // Space -> O(N) stack space 

//  static boolean hasSum(int[] num, int  S) {
//      return hasSumRecursive(num, S, 0);
//   }

  private static boolean hasSumRecursive(int[] num, int remainder, int currentIndex) {

    // Using difference 
    if (remainder == 0) return true; 

    if (currentIndex > num.length - 1) return false; 

    if (remainder - num[currentIndex] == 0) return true; 

    if (num[currentIndex] <= remainder) {
        if (hasSumRecursive(num, remainder - num[currentIndex], currentIndex + 1)) {
            return true; 
        }
    }

    return hasSumRecursive(num, remainder, currentIndex + 1);
  }

  public static void main(String[] args) {
    SubSetSum ps = new SubSetSum();
    int[] num = {1, 2, 3, 7};
    System.out.println(ps.hasSum(num, 6));
  }
}
