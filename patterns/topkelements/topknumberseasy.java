package algorithms; 

/**
Given an unsorted array of numbers, find the ‘K’ largest numbers in it.

Note: For a detailed discussion about different approaches to solve this problem, take a look at Kth Smallest Number.

Runtime O (N * LogK) 
Space O(K) 
 */

import java.util.*;

class KLargestNumbers {

  public static List<Integer> findKLargestNumbers(int[] nums, int k) {

    PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(k, (a,b) -> a - b);

    for (int i = 0; i < nums.length; i++) {
      if (!minHeap.isEmpty()) {
        if (minHeap.peek() >= nums[i]) 
          continue; 

        if (minHeap.size() == k)
          minHeap.poll();
        minHeap.offer(nums[i]);
      } else {
        minHeap.offer(nums[i]);
      }
    }

    return new ArrayList<>(minHeap);
  }

  public static void main(String[] args) {
    List<Integer> result = KLargestNumbers.findKLargestNumbers(new int[] { 3, 1, 5, 12, 2, 11 }, 3);
    System.out.println("Here are the top K numbers: " + result);

    result = KLargestNumbers.findKLargestNumbers(new int[] { 5, 12, 11, -1, 12 }, 3);
    System.out.println("Here are the top K numbers: " + result);
  }
}
