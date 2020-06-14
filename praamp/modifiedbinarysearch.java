package algorithms; 

/**
Given a sorted array arr of distinct integers, write a function indexEqualsValueSearch that returns the lowest index i for which arr[i] == i. Return -1 if there is no such index. Analyze the time and space complexities of your solution and explain its correctness.
 */

import java.io.*;
import java.util.*;

class Solution {
  
  static int indexEqualsValueSearch(int[] arr) {
    // your code goes here
    
    int start = 0; 
    int end = arr.length; 
    
    int bestIndex = -1; 
    
    while (start <= end) {
      int distance = start + end; 
      int midPoint = distance / 2; 
      
      if (arr[midPoint] > midPoint || arr[midPoint] == midPoint) {
        if (arr[midPoint] == midPoint) {
          bestIndex = midPoint; 
        }
         end = midPoint - 1; 
      } else {
         start = midPoint + 1; 
      }
    }
    
    return bestIndex; 
  }
  
  public static void main(String[] args) {
    
    
    
    int[] testCase = new int[]{-8,0,2,5}; 
    
    System.out.println(indexEqualsValueSearch(testCase)); 
    System.out.println(indexEqualsValueSearch(new int[]{1,0,3,6})); 
  }

}