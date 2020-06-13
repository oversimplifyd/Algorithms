package algorithms; 

/**
Given an array, find the sum of all numbers between the K1’th and K2’th smallest elements of that array.
 */
 
import java.util.*;

class SumOfElements {

  public static int findSumOfElements(int[] nums, int k1, int k2) {
    
    if (nums.length < Math.min(k1, k2)) return 0;
    
    PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Math.max(k1, k2), (a, b) -> b - a);
    
    int maxSmallest = Math.max(k1, k2);
    int minSmallest = Math.min(k1, k2);

    for (int i = 0; i < nums.length; i++) { // N * LogK
      maxHeap.offer(nums[i]);
      if (maxHeap.size() > maxSmallest) {
        maxHeap.poll();
      }
    }

    maxHeap.poll();  // removes the k2thSmallest  // LogK

    int sum = 0; 
    while (k2 - 1 > k1) { // (k2 - k1) * Log K
      sum += maxHeap.poll();
      k2--; 
    }

    return sum; 
  }

  public static void main(String[] args) {
    int result = SumOfElements.findSumOfElements(new int[] { 1, 3, 12, 5, 15, 11 }, 3, 6);
    System.out.println("Sum of all numbers between k1 and k2 smallest numbers: " + result);

    result = SumOfElements.findSumOfElements(new int[] { 3, 5, 8, 7 }, 1, 4);
    System.out.println("Sum of all numbers between k1 and k2 smallest numbers: " + result);
  }
}
