package algoithms;

/**
Given an array containing 0s and 1s, if you are allowed to replace no more than ‘k’ 0s with 1s, find the length of the longest contiguous subarray having all 1s.
 */
 
class ReplacingOnes {
  public static int findLength(int[] arr, int k) {
    
    int windowStart = 0; 
    int maxOnesRepeat = 0; 
    int max = 0; 
    
    for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {

      if (arr[windowEnd] == 1 ) {
        maxOnesRepeat++; 
      }
      
      if ((windowEnd - windowStart + 1) - maxOnesRepeat > k) {
        if (arr[windowStart] == 1) {
          maxOnesRepeat--; 
        }
        windowStart++;
      }
      max = Math.max(max, windowEnd - windowStart + 1);
    }

    return max; 
  }
}
