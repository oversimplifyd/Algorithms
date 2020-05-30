package algorithms; 

/**
We are given an array containing ‘n’ distinct numbers taken from the range 0 to ‘n’. Since the array has only ‘n’ numbers out of the total ‘n+1’ numbers, find the missing number.
 */
class MissingNumber {

  public static int findMissingNumber(int[] nums) {
    
    int[] sortedNumber = cyclicSort(nums);

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != i) return i; 
    }
    
    return -1; 
  }

  private static int[] cyclicSort(int[] nums) {
    int i = 0; 
    while (i < nums.length) {
      int j = i; 

      if (nums[j] == i || nums[j] == nums.length) {
        i++; 
      } else {
        //swap 
        int actualIndex = nums[j]; 
        int temp = nums[j];
        nums[j] = nums[actualIndex];
        nums[actualIndex] = temp;
      }
    }
    return nums;
  }
}
