package algorithms; 

import java.util.*;

class QuadrupleSumToTarget {

  public static List<List<Integer>> searchQuadruplets(int[] arr, int target) {

    if (arr.length < 4) {
      throw new IllegalArgumentException("Invalid argument");
    }

    List<List<Integer>> quadruplets = new ArrayList<>();
    Arrays.sort(arr);

    // 1, 2, 3, 9, 10, 9, 19, 11, 12 

    for (int i = 0; i < arr.length - 3; i++) {
      if (i > 0 && arr[i] == arr[i - 1]) {
        continue;
      }

      for (int j = i + 1; j < arr.length - 2; j++) {
        if (arr[j] == arr[j - 1]) {
          continue; 
        }

        int firstPartSum = arr[i] + arr[j];
        if (firstPartSum > target) {
          break;
        }

        int targetSumToFind = target - firstPartSum;
        List<Integer> secondPartSum = findTarget(arr, j+1, arr.length - 1, targetSumToFind);
        List<Integer> completePartSum = new ArrayList<Integer>(4);

        if (secondPartSum.isEmpty()) {
          continue; 
        }

        completePartSum.add(secondPartSum.get(0));
        completePartSum.add(secondPartSum.get(1));
        completePartSum.add(arr[i]);
        completePartSum.add(arr[j]);
        
        quadruplets.add(completePartSum);
      }
    }
    // TODO: Write your code here
    return quadruplets;
  }

  private static List<Integer> findTarget(int[] arr, int firstIndex, int lastIndex, int target) {

    List<Integer> doubles = new ArrayList<>(2);

    int leftPointer = firstIndex;
    int rightPointer = lastIndex;

    while (leftPointer < rightPointer) {
    
      int sum = arr[leftPointer] + arr[rightPointer];
      if (sum == target) {

        int leftValue = arr[leftPointer];
        int rightValue = arr[rightPointer];
        
        doubles.add(leftValue);
        doubles.add(rightValue);

        return doubles;
      } else if (sum > target) {
        rightPointer--; 
      } else {
        leftPointer++; 
      }
    }

    return doubles;
  }
}