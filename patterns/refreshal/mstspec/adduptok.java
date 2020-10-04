import java.util.*; 

class AddUpToK {

    public static boolean addsUp(int[] arr, int k) {

        /**
         * Given an array of numbers and a number k, determine if
         *  there are three entries in the array which add up to the specified number k.
         *  For example, given [20, 303, 3, 4, 25] and k = 49, return true as 20 + 4 + 25 = 49
         * 
         * 
         * 20, 303, 3, 4, 25 
         * 3 4 20 25 383 
         * 
         * 2 4 < k -> find if there is any two sum that adds up to k - (2 + 4)  in the rest of the array 
         * 
         * 
         */

         Arrays.sort(arr);

         for (int i = 0; i < arr.length; i++) {

            int target = k - arr[i];
            if (targetExists(target, i+1, arr)) return true; 
         }

         return false; 
    }

    private static boolean targetExists(int target, int startIndex, int[] arr) {

        int left = startIndex; 
        int right = arr.length - 1; 

        while (left < right) {
            if (arr[left] + arr[right] == target) return true; 

            if (arr[left] + arr[right] > target) {
                right--; 
            } else  {
                left++; 
            }
        }

        return false; 
    }

    public static void main(String[] args) {

        int[] item = {20, 303, 3, 4, 25, -1};
        int k = 28; 

        System.out.println(addsUp(item, k));
    }
}