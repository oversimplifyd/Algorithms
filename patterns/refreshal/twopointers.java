import java.util.*; 

class TwoPointers {

    public static int[] pairWithTargetSum(int[] pairs, int sum) {
        /**
            Given an array of sorted numbers and a target sum, find a pair in the array whose sum is equal to the given target.
            Write a function to return the indices of the two numbers (i.e. the pair) such that they add up to the given target.
            Example 1:
            Input: [1, 2, 3, 4, 6], target=6
            Output: [1, 3]
            1,2,3,4     6 
         */

         int leftPointer = 0; 
         int rightPointer = pairs.length - 1;

         while (leftPointer < rightPointer) {
             int currentSum = pairs[leftPointer] + pairs[rightPointer];

             if (currentSum == sum) {
                 return new int[]{leftPointer, rightPointer};
             }

             if (currentSum > sum) {
                 rightPointer--;
             } else {
                 leftPointer++; 
             }
         }

         return new int[]{};
    }

    public static int removeDuplicate(int[] pairs) {

        if (pairs.length < 2) {
            return pairs.length; 
        }

        /**
             * Given an array of sorted numbers, remove all duplicates from it. You should not use any extra space; after removing the duplicates in-place return the length of the subarray that has no duplicate in it.

            Example 1:

            Input: [2, 3, 3, 3, 6, 9, 9]

            // 2 3 3 3 6 6 9      
            //           a b   
            Output: 4
            Explanation: The first four elements after removing the duplicates will be [2, 3, 6, 9].
         */

         int leftPointer = 0; 
         int rightPointer = leftPointer + 1; 

         int duplicates = 0; 

         while (rightPointer < pairs.length) {
             if (pairs[leftPointer] == pairs[rightPointer]) duplicates++;
             leftPointer++; 
             rightPointer++; 
         }

         return pairs.length - duplicates;
    }

    public static int[] dutchNationalFlag(int[] pairs)
    {
        /**
             * Given an array containing 0s, 1s and 2s, sort the array in-place. You should treat numbers of the array as objects, hence, we can’t count 0s, 1s, and 2s to recreate the array.

            The flag of the Netherlands consists of three colors: red, white and blue; and since our input array also consists of three different numbers that is why it is called Dutch National Flag problem.

            Example 1:

            Input: [1, 0, 2, 1, 0]
            Output: [0 0 1 1 2]

            1 0 2 1 0
            0 0 1 1 2

            /// if number at leftpointer is gretaer than number at right pointer
            // swap and 
         */

         int low = 0; 
         int high = pairs.length -1; 

         for (int i = 0; i <= high;) {

            if (pairs[i] == 0) {
                swap(i, low, pairs);
                i++; 
                low++; 
            } else if (pairs[i] == 1) {
                i++; 
            } else {
                swap(i, high, pairs);
                high--; 
            }
         }

        return pairs;
    }

    public static void swap(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp; 
    }

    public static void main(String[] args) {
        // int[] a = {2, 5, 9, 11};
        // for (int r: pairWithTargetSum(a, 11))
        //     System.out.println(r);

        // int[] a = {2, 3, 3, 3, 6, 9, 9};
        // int[] b = {2, 2, 2, 11};
        // System.out.println(removeDuplicate(b));

        int[] a = {2,0,1,0,1,0,2,0,1};
        for (int r: dutchNationalFlag(a))
            System.out.println(r);
    }
}