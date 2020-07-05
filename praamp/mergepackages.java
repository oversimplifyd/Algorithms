/**
Given a package with a weight limit limit and an array arr of item weights, implement a function getIndicesOfItemWeights that finds two items whose sum of weights equals the weight limit limit. Your function should return a pair [i, j] of the indices of the item weights, ordered such that i > j. If such a pair doesn’t exist, return an empty array.

Analyze the time and space complexities of your solution.

Example:

input:  arr = [4, 6, 10, 15, 16],  lim = 21

output: [3, 1] # since these are the indices of the
               # weights 6 and 15 whose sum equals to 21
 */
import java.io.*;
import java.util.*;

class Solution {

  static int[] getIndicesOfItemWeights(int[] arr, int limit) {
    
    if (arr.length < 2) return new int[]{};
    
    Map<Integer, Integer> differnceMap = new HashMap<>();
    
    int leftIndex = -1; 
    int rightIndex = -1;
    
    for (int i = 0; i < arr.length; i++) {
      if (differnceMap.containsKey(arr[i])) {
        leftIndex = differnceMap.get(arr[i]);
        rightIndex = i; 
      } else {
        differnceMap.put(limit - arr[i], i);
      }
    }
    
    if (leftIndex > -1) return new int[]{rightIndex, leftIndex};
    
    return new int[]{};
  }

  public static void main(String[] args) {

  }

}
