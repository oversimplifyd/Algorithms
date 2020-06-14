package algorithms; 

/**
Given two sorted arrays arr1 and arr2 of passport numbers, implement a function findDuplicates that returns an array of all passport numbers that are both in arr1 and arr2. Note that the output array should be sorted in an ascending order.

Let N and M be the lengths of arr1 and arr2, respectively. Solve for two cases and analyze the time & space complexities of your solutions: M ≈ N - the array lengths are approximately the same M ≫ N - arr2 is much bigger than arr1.

 */
import java.io.*;
import java.util.*;

class Solution {

  static int[] findDuplicates(int[] arr1, int[] arr2) {
    
    List<Integer> duplicates = new ArrayList<>(); 
    
    int leftPointer = 0; 
    int rightPointer = 0; 
    
    while (leftPointer < arr1.length && rightPointer < arr2.length) {
      
      if (arr1[leftPointer] < arr2[rightPointer]) {
        leftPointer++; 
      } else if (arr1[leftPointer] > arr2[rightPointer]) {
        rightPointer++;
      } else {
        duplicates.add(arr1[leftPointer]);
        leftPointer++;
        rightPointer++;
      }
    }
    
    int[] result = new int[duplicates.size()];
    
    for (int i = 0; i < duplicates.size(); i++) {
      result[i] = duplicates.get(i);
    }
    
    return result; 
  }

  public static void main(String[] args) {
    int[] arr1 = new int[]{1, 2, 3, 5, 6, 7}; 
    int[] arr2 = new int[]{3, 6, 7, 8, 20};
    
    System.out.println(findDuplicates(arr1, arr2));
  }

}

// 1 3 5 
// 2 4 6
// [1, 2, 3, 5, 6, 7], 
// [3, 6, 7, 8, 20, 21, 27 , 28]

// N + M
// N^2 

