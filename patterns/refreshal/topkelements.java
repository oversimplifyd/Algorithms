import java.util.*; 

class TopKElements {

    public static int kThSmallestNumber(int[] items, int k)
    {
        /**
         * Given an unsorted array of numbers, find Kth smallest number in it.

            Please note that it is the Kth smallest number in the sorted order, not the Kth distinct element.

            Note: For a detailed discussion about different approaches to solve this problem, take a look at Kth Smallest Number.

            Example 1:

            Input: [1, 5, 12, 2, 11, 5], K = 3
            Output: 5
            Explanation: The 3rd smallest number is '5', as the first two smaller numbers are [1, 2].
            Example 2:

            12 5 1   5 2 1   5 2 1  
            [1, 2]   -> K 
            [2 2 1 2]    2  2  1     2 
         */

         PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, (a, b) -> b - a);

         for (int i = 0; i < k; i++) {
             maxHeap.offer(items[i]);
         }

         // 5 
         // 3 
         // i = 3    5 - 3    2
         for (int i = k; i < items.length; i++ ) {
             if (items[i] < maxHeap.peek()) {
                 maxHeap.poll();
                 maxHeap.offer(items[i]);
             }
         }

         return maxHeap.peek();
    }

    public static List<Point> kThClosestPoint(Point[] items, int k) {
        /**
         * Given an array of points in the a 2D2D plane, find ‘K’ closest points to the origin.

            Example 1:

            Input: points = [[1,2],[1,3]], K = 1
            Output: [[1,2]]
            Explanation: The Euclidean distance between (1, 2) and the origin is sqrt(5).
            The Euclidean distance between (1, 3) and the origin is sqrt(10).
            Since sqrt(5) < sqrt(10), therefore (1, 2) is closer to the origin.
         */

         PriorityQueue<Point> maxHeap = new PriorityQueue<Point>(k, (a, b) -> b.distanceFromOrigin() - a.distanceFromOrigin());

         for (int i = 0; i < k; i++) {
             maxHeap.offer(items[i]);
         }

         for (int i = k; i < items.length; i++) {
             if (Math.sqrt(items[i].distanceFromOrigin()) < maxHeap.peek().distanceFromOrigin()) {
                 maxHeap.poll();
                 maxHeap.offer(items[i]);
             }
         }
         
         return new ArrayList<Point>(maxHeap);
    }

    public static List<Integer> topKFrequentNumbers(int[] items, int k){
        /**
         * Given an unsorted array of numbers, find the top ‘K’ frequently occurring numbers in it.

            Example 1:

            Input: [1, 3, 5, 12, 11, 12, 11], K = 2
            Output: [12, 11]
            Explanation: Both '11' and '12' apeared twice.

            Number -> Frequ 
            12          2
            11          1

            What if k is 0, 
            What if k is greater than items.length

            What if items is empty?  
         */

         if (k > items.length || k == 0) throw new IllegalArgumentException("Invalid input");

         if (items.length == 0) throw new IllegalArgumentException("Items length is 0 ");

         HashMap<Integer, Integer> freqMap = new HashMap<>();

         for (int i = 0; i < items.length; i++) {
             freqMap.put(items[i], freqMap.getOrDefault(items[i], 0) + 1);
         }

         PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((a,b) -> a.getValue() - b.getValue());

         for(Map.Entry<Integer, Integer> entry: freqMap.entrySet()) {
             minHeap.add(entry);
             if(minHeap.size() > k) {
                 minHeap.poll();  
             }
         }

         List<Integer> result = new ArrayList<>();

         while (!minHeap.isEmpty()) {
             result.add(minHeap.poll().getKey());
         }

         return result;
    }

    public static void main(String[] args) {
        // int result = TopKElements.kThSmallestNumber(new int[] { 1, 5, 12, 2, 11, 5 }, 3);
        // System.out.println("Kth smallest number is: " + result);
    
        // // since there are two 5s in the input array, our 3rd and 4th smallest numbers should be a '5'
        // result = TopKElements.kThSmallestNumber(new int[] { 1, 5, 12, 2, 11, 5 }, 4);
        // System.out.println("Kth smallest number is: " + result);
    
        // result = TopKElements.kThSmallestNumber(new int[] { 5, 12, 11, -1, 12 }, 3);
        // System.out.println("Kth smallest number is: " + result);

        List<Integer> result = TopKElements.topKFrequentNumbers(new int[] { 1, 3, 5, 12, 11, 12, 11 }, 2);
        System.out.println("Here are the K frequent numbers: " + result);
    
        result = TopKElements.topKFrequentNumbers(new int[] { 5, 12, 11, 3, 11 }, 2);
        System.out.println("Here are the K frequent numbers: " + result);
    }
}

class Point {
    int x; 
    int y; 

    public Point(int x, int y) {
        this.x = x; 
        this.y = y; 
    }

    public int distanceFromOrigin(){
        return (x*x) + (y*y);
    }
}