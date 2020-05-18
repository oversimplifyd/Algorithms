package algorithms; 

// Problem Statement 
/**Given an array of positive numbers and a positive number ‘S’, find the length of the smallest contiguous subarray whose sum is greater than or equal to ‘S’. Return 0, if no such subarray exists.
 */
class MinSizeSubArraySum {
  public static int findMinSubArray(int S, int[] arr) {
    int min = Integer.MAX_VALUE;
    int windowSum = 0; 
    int windowStart = 0; 

    for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
      windowSum += arr[windowEnd];

      while (windowSum >= S) {
        min = Math.min(min, (windowEnd - windowStart) + 1);
        windowSum -= arr[windowStart];        
        windowStart++;
      }
    }
    
    return Integer.MAX_VALUE == min ? 0 : min;
  }
}
