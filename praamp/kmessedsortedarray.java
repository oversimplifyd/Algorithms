/**
Given an array of integers arr where each element is at most k places away from its sorted position, code an efficient function sortKMessedArray that sorts arr. For instance, for an input array of size 10 and k = 2, an element belonging to index 6 in the sorted array will be located at either index 4, 5, 6, 7 or 8 in the input array.

Analyze the time and space complexities of your solution.
 */

import java.io.*;
import java.util.*;

class Solution {

  static int[] sortKMessedArray(int[] arr, int k) {
    
    if (arr.length == 1) return arr; 
    
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, (a, b) -> a - b);
    
    // 1, 4
    //O(K)
    for (int i = 0; i < k; i++) {
      minHeap.offer(arr[i]);
    }
    
    //O(NlogK)
    // [1, 4, 5, 2, 3, 7, 8, 6, 10, 9]
    int index = 0; 
    for (int i = k; i < arr.length; i++) {
      if (arr[i] < minHeap.peek()) {
        arr[index] = arr[i];
      } else {
        int currentValue = minHeap.poll();
        minHeap.offer(arr[i]);
        arr[index] = currentValue; 
      }
      
      index++; 
    }
    
    // 8, 9
    // O(K)
    while (!minHeap.isEmpty()) {
      arr[index] = minHeap.poll();
      index++; 
    }
    
    return arr; 
  }

  public static void main(String[] args) {

  }

}
