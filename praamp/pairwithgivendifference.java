import java.io.*;
import java.util.*;

class Solution {

  static int[][] findPairsWithGivenDifference(int[] arr, int k) {
    
    if (arr.length < 2) return new int[][]{}; 
    
    Map<Integer, Integer> intMap = new HashMap<>();    
    
    // [1,5,11,7], 4
    // 1 - 4 |  -3 -> 0 
    // 5 - 4 |   1  -> 1
    // 11 - 4 :  7 ->  2 
    // 7 -4 :    3 ->    3 
    
    for (int i = 0; i < arr.length; i++) {
      intMap.put(arr[i] - k, i);
    }
    
    int numberOfPairs = 0; 
    for (int number: arr) {
      if (intMap.containsKey(number)) {
        numberOfPairs++; 
      }
    }
    
    int[][] result = new int[numberOfPairs][2];
    
    if (numberOfPairs > 0) {
       int index = 0; 
       for (int i = 0; i < arr.length; i++) {
        if (intMap.containsKey(arr[i])) {
          result[index][1] = arr[i]; 
          result[index][0] = arr[intMap.get(arr[i])];
          
          index++; 
        }
      }
    }
    
    return result;
  }

  public static void main(String[] args) {

  }
}
