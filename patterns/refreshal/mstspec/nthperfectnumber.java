import java.util.*; 

class NthPerfectNumber {

    public static int nthPerfect(int digits) {
        /**
         * A number is considered perfect if its digits sum up to exactly 10.

            Given a positive integer n, return the n-th perfect number.

            For example, given 1, you should return 19. Given 2, you should return 28.


            1   ->      -> 19    -> 10 / 10 -> 1 | 10 % 10 ->0 
            11  -> 8    -> 118   -> 11 / 10 -> 1 | 11 % 10 -> 1 
            23  -> 5    -> 235   -> 23 % 10 -> 3 | 23 / 10 -> 2  
            45  -> 1    -> 451   -> 45 % 10 -> 5 | 45 / 10 -> 4    
            1232 ->     ->       -> 1232 / 10 -> 123          2
                                              -> 12           3
                                              -> 1            2
                                              -> 0            1

                                    45  / 10  -> 4          5
                                    4 / 10       0          4
         */

         if (digits <= 0) return -1; 
         
         int result = nthPerfectRecursive(digits, 0); 

         if (result < 0) return -1; 

         String finalResult = String.valueOf(digits) + String.valueOf(result);

         return Integer.valueOf(finalResult);
    }

    private static int nthPerfectRecursive(int digits, int sum) {

        if (sum > 10) return -1; 

        if (digits == 0) return 10 - sum; 

        return nthPerfectRecursive(digits / 10, sum + (digits % 10));
    }

    public static void main(String[] args) {
        System.out.println(nthPerfect(178984));
    }
}
