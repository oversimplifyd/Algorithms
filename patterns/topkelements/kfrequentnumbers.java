package algorithms; 

/**
Given an unsorted array of numbers, find the top ‘K’ frequently occurring numbers in it.

Runtime N + NLogK 
 */
import java.util.*;

class TopKFrequentNumbers {

  public static List<Integer> findTopKFrequentNumbers(int[] nums, int k) {
    List<Integer> topNumbers = new ArrayList<>(k);
    
    HashMap<Integer, Integer> freqMap = new HashMap<Integer, Integer>();

    PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<Map.Entry<Integer, Integer>>((a, b) -> a.getValue() - b.getValue()); 

    for (int n : nums) 
      freqMap.put(n, freqMap.getOrDefault(n, 0) + 1);
    
    for (Map.Entry<Integer, Integer> item: freqMap.entrySet()) {
      minHeap.add(item);
      if (minHeap.size() > k) minHeap.poll();
    }

    while (k > 0) {
      topNumbers.add(minHeap.poll().getKey());
      k--; 
    }

    return topNumbers;
  }

  public static void main(String[] args) {
    List<Integer> result = TopKFrequentNumbers.findTopKFrequentNumbers(new int[] { 1, 3, 5, 12, 11, 12, 11 }, 2);
    System.out.println("Here are the K frequent numbers: " + result);

    result = TopKFrequentNumbers.findTopKFrequentNumbers(new int[] { 5, 12, 11, 3, 11 }, 2);
    System.out.println("Here are the K frequent numbers: " + result);
  }
}
