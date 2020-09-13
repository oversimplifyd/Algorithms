import java.util.*; 

class ModifiedSearch {

    public static int modSearch1(int[] items, int key)
    {
        /**
         * Given a sorted array of numbers, find if a given number ‘key’ is present in the array. Though we know that the array is sorted, we don’t know if it’s sorted in ascending or descending order. You should assume that the array can have duplicates.

            Write a function to return the index of the ‘key’ if it is present in the array, otherwise return -1.

            Example 1:

            Input: [4, 6, 10], key = 10
            Output: 2

            [1, 2, 3, 4, 5, 6, 7], key = 5
         */

         if (items.length == 1) {
             if (items[0] == key) return 0; 
             
             return -1; 
         }

         int start = 0; 
         int end = items.length - 1; 

         boolean ascending = items[0] < items[1];

         while (start <= end) {
             int mid = start + (end - start) / 2;
             // int mid = (end + start) / 2 another alterntive 

             if (items[mid] == key) return mid; 

             if (ascending) {
                if (items[mid] > key) {
                    end = mid - 1; 
                } else {
                    start = mid + 1; 
                }
             } else {
                if (items[mid] > key) {
                    start = mid + 1;
                } else {
                    end = mid - 1; 
                }
             }
         }

         return -1;
    }

    public static int ceilingOfNumber(int[] items, int key)
    {
        /**
         * Given an array of numbers sorted in an ascending order, find the ceiling of a given number ‘key’. The ceiling of the ‘key’ will be the smallest element in the given array greater than or equal to the ‘key’.

            Write a function to return the index of the ceiling of the ‘key’. If there isn’t any ceiling return -1.

            Example 1:

            Input: [4, 6, 10], key = 6

            1 2 3 4 6 8 9 10 12 13    7
            Output: 1
            Explanation: The smallest number greater than or equal to '6' is '6' having index '1'.
         */

         int start = 0; 
         int end = items.length - 1; 

         // 2 3 4 6 8 9 10          11
         while (start < end) {
             int mid = start + (end - start) / 2; 

             if (items[mid] >= key) {
                 end = mid; 
             } else {
                 start = mid + 1; 
             }
         }

         if (end == start && items[end] < key) return -1; 

         return end; 
    }

    public static int minDifference(int[] items, int key) {
        /**
         * Given an array of numbers sorted in ascending order, find the element in the array that has the minimum difference with the given ‘key’.

            Example 1:

            Input: [4, 5, 6], key = 7
            Output: 6
            Explanation: The difference between the key '7' and '6' is minimum than any other number in the array 

            Input: [1, 3, 8, 10, 15], key = 12
            // Biggest number less than or equal to 12 || 
            // Smallest number greater than or equal to 12 

            1 3 8 14 15    12
            2 4 6 8 9 10 11 17 20   12,  11

            4 6 10     8

            key - item[start] > key  - item[end] return end else return start
         */

        if (key < items[0]) return items[0];

        if (key > items[items.length - 1]) return items[items.length - 1];

         int start = 0; 
         int end = items.length - 1; 

         while (start <= end) {
             int mid = start + (end - start) / 2; 

             if (items[mid] == key) return items[mid];

             if (items[mid] > key) {
                 end = mid - 1; 
             } else {
                 start = mid + 1; 
             }
         }

         return (Math.abs(items[start] - key) > Math.abs(items[end] - key)) ? items[end] : items[start]; 
    }

    public static void main(String[] args) {

        // int[] items = {1, 2, 3, 4, 5, 7, 9, 10}; 
        // int[] items2 = {10, 9, 8, 7, 4, 3, 2, 1};
        // int key = 6; 
        //System.out.println(ModifiedSearch.modSearch1(items2, key));
        //System.out.println(ModifiedSearch.ceilingOfNumber(items, key));

        // System.out.println(ModifiedSearch.ceilingOfNumber(new int[] { 4, 6, 10 }, 6));
        // System.out.println(ModifiedSearch.ceilingOfNumber(new int[] { 1, 3, 8, 10, 15 }, 12));
        // System.out.println(ModifiedSearch.ceilingOfNumber(new int[] { 4, 6, 10 }, 17));
        // System.out.println(ModifiedSearch.ceilingOfNumber(new int[] { 4, 6, 10 }, -1));

        System.out.println(ModifiedSearch.minDifference(new int[] { 4, 6, 10 }, 7));
        System.out.println(ModifiedSearch.minDifference(new int[] { 4, 6, 10 }, 4));
        System.out.println(ModifiedSearch.minDifference(new int[] { 1, 3, 8, 10, 15 }, 12));
        System.out.println(ModifiedSearch.minDifference(new int[] { 4, 6, 10 }, 17));
    }
}