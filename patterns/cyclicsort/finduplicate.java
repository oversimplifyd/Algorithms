package algorithms; 

/**
We are given an unsorted array containing ‘n+1’ numbers taken from the range 1 to ‘n’. The array has only one duplicate but it can be repeated multiple times. Find that duplicate number without using any extra space. You are, however, allowed to modify the input array.
**/ 

class FindDuplicate {

  public static int findNumber2(int[] nums) {
    
    int i = 0; 

    while (i < nums.length) {
      int j = i; 

      if (nums[i] != i + 1 ) {
        if (nums[i] != nums[nums[i] - 1]) {
          int actualIndex = nums[j] - 1; 
          int temp = nums[j];
          nums[j] = nums[actualIndex];
          nums[actualIndex] = temp; 
        } else {
          return nums[i]; //duplicate 
        }
      } else {
        i++; 
      }
    }
    return -1; 
  }

  // Using fast and slow pointer 
  // Advantage, no in place modification 
  // Finds the duplicate in O(N) and O(1) space 
  // Also, if this type. of a problem has only one duplicate, 
  // we can use the triangular series where n (n+1) / 2 gives the sum of the expected result 
  // and totalArray sum - seriesSum = duplicate 

  public static int findNumber(int[] nums) {

    int fast = 0; 
    int slow = 0; 

    //[2, 1, 3, 3, 5, 4]
    do {
      slow = nums[slow];
      fast = nums[nums[fast]];
    } while (slow != fast);

    int current = slow; 
    int cycleLength = 0; 
    do {
      current = nums[current];
      cycleLength++; 
    } while (current != slow);

    return findCycleStart(nums, cycleLength);
  }

  private static int findCycleStart(int[] nums, int cycleLength) {

    int pointer1 = 0; 
    int pointer2 = 0; 

    // advances the first the length of the cycle 
    while (cycleLength > 0) {
      pointer1 = nums[pointer1];
      cycleLength--; 
    }

    // move both pointers at the same time until the collide. 
    while (pointer1 != pointer2) {
      pointer1 = nums[pointer1];
      pointer2 = nums[pointer2];
    }

    return pointer1; 
  }
}
