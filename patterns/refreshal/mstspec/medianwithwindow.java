import java.util.*; 

class MediaWithWindowSize {

    public static List<Integer> findMedian(int[] arr, int k) {

        /**
         * Given an array of numbers arr and a window of size k, print out the median of each window of size k starting from the left and moving right by one position each time.

            For example, given the following array and k = 3:

            [-1, 5, 13, 8, 2, 3, 3, 1]
            -1 5 13 
             5 13 8
             13 8 2 
             ..... 

             - re-arange the numbers 
             - if even -> average of the two middle numbers 
             - if odd -> take the middle number 

             13          5, -1  -> -1, 5, 13
             13          8, 5   -> 5, 8, 13
             13          8, 2
             8           3, 2
             3           3, 2
             3           3, 1 
             
             -1 5, 13, 8, 2
            
             13 5, 8, -1, 2, 3 3
            
             minHeap     maxHeap  -> using k / 2 
             8 13        5 2 -1 

             1filled        
             and > peek
             8 13           5 3 2

             -1 5, 13, 8, 2, 3 3 1
             1 2 3 3 8 13 

             8 13       3 3 2
             3 8 13     3 2 1 
             5 + 3 / 2 = 4 

             if (number to remove is >= median ) {
                 replace element in minHeap by maxHeap.peek() 
                 insert new number into maxHeap 

                 take median: 
             } else {
                 pop item from maxHeap / repace by new number 
                 take median: 
             }
             
             Time -> N * KlogK 
             Space -> 0(K)
         */

         PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b) -> a - b);
         PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> b - a);

         List<Integer> medians = new ArrayList<>();

         int windowStart = 0; 

         for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {

            minHeap.add(arr[windowEnd]);
            if (minHeap.size() > k / 2 ) {
                int lastItem = minHeap.poll();
                maxHeap.add(lastItem);
            }

            if (windowEnd - windowStart + 1 == k) {
                int median = 0; 
                if (minHeap.size() == maxHeap.size()) {
                    median = (minHeap.peek() + maxHeap.peek()) / 2;
                } else {
                    median = maxHeap.peek();
                }

                medians.add(median);

                if (arr[windowStart] < minHeap.peek()) {
                    maxHeap.remove(arr[windowStart]);
                } else {
                    minHeap.remove(arr[windowStart]);
                    minHeap.add(maxHeap.poll());
                }

                windowStart++; 
            }
         }

         return medians; 
    }

    public static void main(String[] args) {
        int[] items = {-1, 5, 13, 8, 2, 3, 3, 1};
        System.out.println(findMedian(items, 3));
    }
}