package algorithms; 

/**
Given an unsorted array containing numbers, find the smallest missing positive number in it.
 */
class FirstMissingPositive {

  public static int findNumber(int[] nums) {
    // TODO: Write your code here
    // 3 2 5 1 
    // 1 2 5 3 
    // 2 1 5 3 
    // 5 1 2 3 
    
    // 5 2 3 1 
    // 5 2 3 1 
    // 1 2 3 5 

    // 3 -2 0 1 2 
    // 0 -2 3 1 2 
    // 1 -2 3 0 2 
    // 1 2 3 0 -2 

    int i = 0; 

    while ( i < nums.length) {

      if (nums[i] != i + 1 && nums[i] > 0 && nums[i] < nums.length) {
        int actualIndex = nums[i] - 1; 
        int temp = nums[i]; 
        nums[i] = nums[actualIndex];
        nums[actualIndex] = temp; 
      } else {
        i++; 
      }
    }

    int minPositive = nums.length; 

    for (int j = 0; j < nums.length; j++) {
      if (nums[j] <= 0 || nums[j] > nums.length - 1) {
        minPositive = Math.min(minPositive, j+1);
      }
    }

    return minPositive;
  }
}
