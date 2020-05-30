package algorithms; 

/** 
We are given an unsorted array containing numbers taken from the range 1 to ‘n’. The array can have duplicates, which means some numbers will be missing. Find all those missing numbers.
**/
import java.util.*;

class AllMissingNumbers {

  // 2 3 2 1 
  // 3 2 2 1 
  // 2 2 3 1
  // 2 2 3 1
  // 1 2 3 2 

  public static List<Integer> findNumbers(int[] nums) {
    List<Integer> missingNumbers = new ArrayList<>();

    int[] result = cyclicSortWithDuplicateCheck(nums);
    for (int i = 0; i < result.length; i++) {
      if (result[i] != i+1) {
        missingNumbers.add(i+1);
      }
    }
    return missingNumbers;
  }

  private static int[] cyclicSortWithDuplicateCheck(int[] nums) {

    int i = 0; 

    while (i < nums.length) {
      int j = i; 

      // also insetead of doing this double check, I could just bring the else part 
      // up to become the if part such that, I only want to swap if the current number 
      // at current index is not equal to the number else continue 

      if (nums[j] - 1 == i || nums[j] == nums[nums[j] - 1]) {
        i++; 
      } else {
        int actualIndex = nums[j] - 1; 
        int temp = nums[j];
        nums[j] = nums[actualIndex];
        nums[actualIndex] = temp; 
      }
    }

    return nums; 
  }
}
