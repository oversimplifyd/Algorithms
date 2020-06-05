/**
Subset with duplicate 
Same comlexity in time and space as without duplicates 
 */
 
import java.util.*;

class SubsetWithDuplicates {

  public static List<List<Integer>> findSubsets(int[] nums) {

    // 3 1 3 
    // []
    // [3].         
    // [1]  
    // [3, 1]
    // [3, 3]
    // [3, 1, 3]

    Arrays.sort(nums); // NLogN 
    List<List<Integer>> subsets = new ArrayList<>();
    
    subsets.add(new ArrayList<Integer>());

    for (int i = 0; i < nums.length; i++) {

      int currentSize = subsets.size(); 

      int startIndex = 0; 
      if (i > 0 && nums[i] == nums[i-1]) {
        startIndex = currentSize - 2; 
      }

      for (int j = startIndex; j < currentSize; j++) {
        List<Integer> newList = new ArrayList<>(subsets.get(j));
        newList.add(nums[i]);
        subsets.add(newList);
      }
    }
    
    return subsets;
  }

  public static void main(String[] args) {
    List<List<Integer>> result = SubsetWithDuplicates.findSubsets(new int[] { 1, 3, 3 });
    System.out.println("Here is the list of subsets: " + result);

    result = SubsetWithDuplicates.findSubsets(new int[] { 1, 5, 3, 3 });
    System.out.println("Here is the list of subsets: " + result);
  }
}
