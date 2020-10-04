import java.util.*;

class LargestSubArrayWithDistinctElements  {

    public static int largest(int[] items) {

        /**
             * Given an array of elements, return the length of the longest subarray where all its elements are distinct.

    For example, given the array [5, 1, 3, 5, 2, 3, 4, 1], return 5 as the longest subarray of distinct elements is [5, 2, 3, 4, 1].
         */

         int windowStart = 0; 
         HashMap<Integer, Integer> charIndexMap = new HashMap<>();

         int maxLength = 0;

         for (int windowEnd = 0; windowEnd < items.length; windowEnd++) {

            int rightInteger = items[windowEnd];

            if (charIndexMap.containsKey(rightInteger)) {
                windowStart = Math.max(windowStart, charIndexMap.get(rightInteger) + 1);
            }

            charIndexMap.put(rightInteger, windowEnd);

            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
         }

         return maxLength;
    }

    public static void main(String[] args) {

        int[] items = {5, 1, 3, 5};
        System.out.println(largest(items));
    }
}