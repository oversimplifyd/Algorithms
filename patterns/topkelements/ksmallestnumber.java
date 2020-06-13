package algorithms; 

/**
Given an unsorted array of numbers, find Kth smallest number in it.

Please note that it is the Kth smallest number in the sorted order, not the Kth distinct element.

Note: For a detailed discussion about different approaches to solve this problem, take a look at Kth Smallest Number.

Runtime (KLogK + N-K*LogK)
space O(K) 
 */
import java.util.*;

class KthSmallestNumber {

  public static int findKthSmallestNumber(int[] nums, int k) {

    PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k, (a,b) -> b -a); 

    for (int i = 0; i < k; i++) { // K * logK
      maxHeap.add(nums[i]);
    }

    for (int i = k; i < nums.length; i++) {  // O(N-K) * LogK
      if (nums[i] < maxHeap.peek()) {
        maxHeap.poll();
        maxHeap.offer(nums[i]);
      }
    }

    return maxHeap.peek();
  }

  public static void main(String[] args) {
    int result = KthSmallestNumber.findKthSmallestNumber(new int[] { 1, 5, 12, 2, 11, 5 }, 3);
    System.out.println("Kth smallest number is: " + result);

    // since there are two 5s in the input array, our 3rd and 4th smallest numbers should be a '5'
    result = KthSmallestNumber.findKthSmallestNumber(new int[] { 1, 5, 12, 2, 11, 5 }, 4);
    System.out.println("Kth smallest number is: " + result);

    result = KthSmallestNumber.findKthSmallestNumber(new int[] { 5, 12, 11, -1, 12 }, 3);
    System.out.println("Kth smallest number is: " + result);
  }
}
