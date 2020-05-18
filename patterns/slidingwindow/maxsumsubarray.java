package algorithms; 

//Using the Sliding Window Pattern 
 //This essentially solves the problem of re-computing overlapping part of the subarray multip times 
 // Thus, cutting down complexity from O(N^2) to O(N)  

class MaxSumSubArrayOfSizeK {
  public static int findMaxSumSubArray(int k, int[] arr) {
    int maxSum = 0; 
    int windowStart = 0; 
    int windowSum = 0; 

    for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {

      windowSum += arr[windowEnd];

      if (windowEnd >= k - 1) {
        maxSum = Math.max(windowSum, maxSum);
        windowSum -= arr[windowStart];
        windowStart++; 
      }
    }
    return maxSum;
  }
}
