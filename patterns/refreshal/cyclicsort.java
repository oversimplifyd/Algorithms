import java.util.*; 

class CyclicSort {

    public static int[] cyclicSort1(int[] items){

        /**
         * We are given an array containing ‘n’ objects. Each object, when created, was assigned a unique number from 1 to ‘n’ based on their creation sequence. This means that the object with sequence number ‘3’ was created just before the object with sequence number ‘4’.

            Write a function to sort the objects in-place on their creation sequence number in O(n)O(n) and without any extra space. For simplicity, let’s assume we are passed an integer array containing only the sequence numbers, though each number is actually an object.

            Example 1:

            Input: [3, 1, 5, 4, 2]
            Output: [1, 2, 3, 4, 5]
         */

         // 3 1 5 4 2 
         // 5 1 3 4 2 
         // 2 1 3 4 5 
         // 1 2 3 4 5 

         //1 2 3 4 5 
         //math -> position o element = arr[i] - 1 
         // 
         // array contains unique object  -> each number doesn't appear twice 

         int i = 0; 
         while (i < items.length) {
            if (i + 1 != items[i]) {
                int temp = items[i];
                int destinatinPosition = items[i] - 1;
                items[i] = items[destinatinPosition];
                items[destinatinPosition] = temp; 
            } else {
                i++; 
            }
         }

         return items;
    }

    public static int duplicateNumbers(int[] items){
        /**
         * We are given an unsorted array containing ‘n+1’ numbers taken from the range 1 to ‘n’. The array has only one duplicate but it can be repeated multiple times. Find that duplicate number without using any extra space. You are, however, allowed to modify the input array.

            Example 1:

            Input: [1, 4, 4, 3, 2]
            Output: 4

            1 4 4 3 2 
            1 2 3 4 4
         */
        
         int i = 0; 
        
         // 1, 4, 4, 3, 2
         // 1  3  4  4  2
         // 1  4  3  4  2
         // 1  4
         while (i < items.length) {
             if (i + 1 != items[i]) {
                 int temp = items[i];
                 int destinationTarget = items[i] - 1;

                 if (items[i] == items[destinationTarget]) return items[i];
                 
                 items[i] = items[destinationTarget];
                 items[destinationTarget] = temp;
             } else {
                 i++; //continue 
             }
         }

         throw new IllegalArgumentException("No such number");
    }

    public static void main(String[] args) {
        // int[] items = {3, 1, 5, 4, 2};
        // int[] result = CyclicSort.cyclicSort1(items);
        // for(int i = 0; i < result.length; i++) {
        //     System.out.println(items[i]);
        // }

        int[] items = {1, 4, 4, 3, 2};
        int result = CyclicSort.duplicateNumbers(items);

        System.out.println(result);
    }
}