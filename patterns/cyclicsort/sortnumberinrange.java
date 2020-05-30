package algorithms; 

// Runtime -> O(N) 
// Space -> O(1) 
class CyclicSort {

  public static void sort(int[] nums) {
    
    // Same as the while loop below, but the whiile loop looks cleaner. 
    // for (int i = 0; i < nums.length; i++) {
    //   if (nums[i] - 1 == i) {
    //     continue; 
    //   } else {
    //     int j = i; 
    //     while (nums[j] - 1 != i) {
    //       int actualIndex = nums[j] - 1; 
    //       int value = nums[j];
    //       nums[j] = nums[actualIndex];
    //       nums[actualIndex] = value;
    //     }
    //   }
    // }

    int i = 0; 
    while (i < nums.length) {
      int j = i; 
      if (nums[i] - 1 == i) {
        i++; 
      } else {
        int actualIndex = nums[j] - 1; 
        int value = nums[j];
        nums[j] = nums[actualIndex];
        nums[actualIndex] = value;
      }
    }
  }
}
