package algorithms; 

/**
Time Complexity O(N * N!) 
Space -> Same. 
 */
 import java.util.*;

class Permutations {

  public static List<List<Integer>> findPermutations(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    
    // This follow a typical in order level traversal (BFS) 
    Queue<List<Integer>> permutations = new LinkedList<>(); 

    permutations.offer(new ArrayList<>()); 

    for (int currentNumber : nums) {

      int sizeAtcurrentLevel = permutations.size(); // levelSize 

      while (sizeAtcurrentLevel > 0) {
        List<Integer> currentList = permutations.poll();

        // Adds the currentNumber to every position of every sublist of this curent list -> permutate! 
        for (int i = 0; i <= currentList.size(); i++) {
          List<Integer> newSubList = new ArrayList<Integer>(currentList);
          newSubList.add(i, currentNumber);

          if (newSubList.size() == nums.length) {
            result.add(newSubList);
          } else {
            permutations.offer(newSubList);
          }
        }

        sizeAtcurrentLevel--; 
      }
    }

    return result;
  }

  public static void main(String[] args) {
    List<List<Integer>> result = Permutations.findPermutations(new int[] { 1, 3, 5 });
    System.out.print("Here are all the permutations: " + result);
  }
}
