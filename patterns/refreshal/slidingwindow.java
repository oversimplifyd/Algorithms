import java.util.*; 

class SlidingWindow {

    // Shrinking 
    // Expanding 
    // Sliding 

    public static int maxSubArray(int[] list, int k)
    {
        /**
         * Given an array of positive numbers and a positive number ‘k’, find the maximum sum of any contiguous subarray of size ‘k’.

        Example 1:

        Input: [2, 1, 5, 1, 3, 2], k=3 
        Output: 9
        Explanation: Subarray with maximum sum is [5, 1, 3].

        2 1 5 1 3 2 
        2 1 5 
        1 5 1
        5 1 3 
        1 3 2 
        */
        
        int windowStart = 0; 
        int max = Integer.MIN_VALUE;
        int currentSum = 0; 

        // exapnd + slide 
        // 2 1 5 1 3 2 
        for (int windowEnd = 0; windowEnd < list.length; windowEnd++) {

            currentSum += list[windowEnd];

            if (windowEnd - windowStart + 1 == k) {
                max = Math.max(max, currentSum);
                currentSum = currentSum - list[windowStart];
                windowStart++; 
            }
        }

        return max; 
    }

    public static int smallestContSubarray(int[] list, int s)
    {
        /***
             * Given an array of positive numbers and a positive number ‘S’, find the length of the smallest contiguous subarray whose sum is greater than or equal to ‘S’. Return 0, if no such subarray exists.

            Example 1:

            Input: [2, 1, 5, 2, 3, 2], S=7 
            Output: 2
            Explanation: The smallest subarray with a sum great than or equal to '7' is [5, 2].

            3 1 5 2 3 2  7 
            3 1 5 
              1 5 
              1 5 2
                5 2
                5 2 3
                  2 3
                  2 3 5 
                    3 5 
         */

         int windowStart = 0; 
         int currentSum = 0; 
         int minLength = Integer.MAX_VALUE;

         //expand + shrink 
         // 2, 1, 5, 2, 8  7
         for (int windowEnd = 0; windowEnd < list.length; windowEnd++) {

            currentSum += list[windowEnd];

            while (currentSum > s) {
                minLength = Math.min(minLength, windowEnd - windowStart + 1);
                currentSum -= list[windowStart];
                windowStart++; 
            }
         }

         return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public static int substringKdistinctChars(String s, int k)
    {
        /***
         * Given a string, find the length of the longest substring in it with no more than K distinct characters.

        Example 1:

        Input: String="araaci", K=2
        Output: 4
        Explanation: The longest substring with no more than '2' distinct characters is "araa".

        a r a a c  -> 3 
          r a a c  -> 3
            a a c i -> 3 
              a c i -> 3 
                c i -> 2
        
        
        a -> 3   -> 2  -> 1 -> 1
        r -> 1   -> 0 
        c -> 1   -> 
        i -> 1 

        charFreeqMap 
        auxMap 

        if (auxMap.contains(char)) {
            auxMap(char) += 1
        } else  {
            auxMax(har) += 1 
            match++; 
        }

        if (match == k) getMax;

        while (match > k) {
               removetheFirstchar, shrink 
               if auxMap[char] == 0 match--; 
               windowStart++
        }

         */

         int windowStart = 0; 
         HashMap<Character, Integer> map = new HashMap<>();
         int match = 0; 
         int longest = 0; 

         for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
             char currentChar = s.charAt(windowEnd);

             // a r a a c i 
             if (map.containsKey(currentChar)) {
                 if (map.get(currentChar) == 0) match++; 
                 map.put(currentChar, map.get(currentChar) + 1);
             } else {
                map.put(currentChar, 1);
                match++; 
             }

             //aa
             if (match == k) {
                 longest = Math.max(longest, windowEnd - windowStart + 1);
             }

             // a r a a c i 
             while (match > k) {
                 char leftMostChar = s.charAt(windowStart);
                 if (map.containsKey(leftMostChar)) {
                    map.put(leftMostChar, map.get(leftMostChar) - 1);
                    if (map.get(leftMostChar) == 0) match--;
                 }

                 windowStart++;
             }
         }

         return longest;
    }

    public static int fruitToBasket(char[] fruits)
    {
        /**
         * Given an array of characters where each character represents a fruit tree, you are given two baskets and your goal is to put maximum number of fruits in each basket. The only restriction is that each basket can have only one type of fruit.

        You can start with any tree, but once you have started you can’t skip a tree. You will pick one fruit from each tree until you cannot, i.e., you will stop when you have to pick from a third fruit type.

        Write a function to return the maximum number of fruits in both the baskets.

        Example 1:

        Input: Fruit=['A', 'B', 'C', 'A', 'C']
        Output: 3

        Expand + Shrink 
        A B C A C D D D A
        A B
          B C 
            C A C
                C D D D 
                  D D  D A 
         */

        int windowStart = 0; 
        int maxSum = Integer.MIN_VALUE;
        int match = 0; 

        HashMap<Character, Integer> map = new HashMap<>();

        for (int windowEnd = 0; windowEnd < fruits.length; windowEnd++) {

            char currentFruit = fruits[windowEnd];

            if (map.containsKey(currentFruit)) {
                if (map.get(currentFruit) == 0) match++; 
                map.put(currentFruit, map.get(currentFruit) + 1);
            } else {
                map.put(currentFruit, 1);
                match++;
            }

            if (match == 2) {
                maxSum = Math.max(maxSum, windowEnd - windowStart + 1);
            }

            while (match > 2) {
                char leftMostFruit = fruits[windowStart];
                if (map.containsKey(leftMostFruit)) {
                    map.put(leftMostFruit, map.get(leftMostFruit) - 1);
                    if (map.get(leftMostFruit) == 0) match--; 
                }
                windowStart++;
            }
        }

        return maxSum == Integer.MIN_VALUE  ? 0 : maxSum;
    }

    public static String getShortestUniqueSubstring(char[] uniqueChars, String s)
    {
        /***
         * Given an array of unique characters arr and a string str, Implement a function getShortestUniqueSubstring 
         * that finds the smallest substring of str containing all the characters in arr. Return "" (empty string) if such a substring doesn’t exist.

            Come up with an asymptotically optimal solution and analyze the time and space complexities.

            Example:

            input:  arr = ['x','y','z'], str = "xyyzyzyx"

            output: "zyx"

            xyyzyzyx
            x y y z  -> 4 
              y y z y z y x -> 7 
                y z y z y x -> 6 
                  z y z y x -> 5 
                    y z y x - 4 
                      z y x - 3 
            x - 2 
            y - 4 
            z - 3 

            how do I check if it contains all chars in arrray, match == length of charArray 
             x -> 3 
             y -> 3 
             y -> 2 

             x -> 1
             y -> 2
             z -> 1 

             while (match == k.length) {

             }
         */

         int windowStart = 0; 
         int minSub = Integer.MAX_VALUE;
         int match = 0; 
         int subStart = 0; 
         int subEnd = 0; 

         HashMap<Character, Integer> map = new HashMap<>(); 

         for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
             char currentChar = s.charAt(windowEnd);

             if (map.containsKey(currentChar)) {
                 map.put(currentChar, map.get(currentChar) + 1);
             } else {
                 map.put(currentChar, 1);
                 match++; 
             }

             while (match == uniqueChars.length) {

                if (windowEnd - windowStart + 1 < minSub) {
                    minSub = windowEnd - windowStart + 1; 
                    subStart = windowStart;
                    subEnd = windowEnd;

                    if (minSub == uniqueChars.length) {
                        return s.substring(windowStart, windowEnd+1);
                    }
                }

                 char leftMostChar = s.charAt(windowStart);
                 if (map.containsKey(leftMostChar)) {
                     map.put(leftMostChar, map.get(leftMostChar) - 1); 

                     if (map.get(leftMostChar) == 0) {
                         match--; 
                         map.remove(leftMostChar);
                     }
                 }
                 windowStart++;
             }
         }

         return minSub == Integer.MAX_VALUE ? "" : s.substring(subStart, subEnd+1);
    }

    public static int stringMatchPattern(String s, String pattern){

         int windowStart = 0; 
          Map<Character, Integer> charFreeqMap = new HashMap<>();

          /**
           * o n i o n i o n s p l 
           * o n i o n 
           * 
           * o -> 2 
           * n -> 2 
           * i -> 1 
           * o -> 1 
           * s -> 1 
           * 
           * 
           * 
           * 
           *  o n i o n i o n s p l 
           * 
           * o -> 2   -> 1   -> 0
           * n -> 2   -> 1   -> 0 
           * i -> 1   -> 0 
           * s -> 1
           * 
           * 
           * match = 5
           */
           int totalMatches = 0; 
           int matches = 0; 
           char[] stringChar = s.toCharArray();
           char[] patternChar = pattern.toCharArray();

           for (char c: patternChar) {
               charFreeqMap.put(c, charFreeqMap.getOrDefault(c, 0) + 1);
           }

           for (int windowEnd = 0; windowEnd < stringChar.length; windowEnd++) {
               char rightChar = stringChar[windowEnd];

               if (charFreeqMap.containsKey(rightChar)) {
                   charFreeqMap.put(rightChar, charFreeqMap.get(rightChar) - 1);
                   if (charFreeqMap.get(rightChar) == 0) {
                       matches++; 
                   }
               }

               if (windowEnd - windowStart + 1 == patternChar.length) {

                   if (matches == charFreeqMap.size() && isAMatch(windowStart, s, pattern)) {
                       totalMatches++; 
                   } 

                   char leftChar = stringChar[windowStart];
                   if (charFreeqMap.containsKey(leftChar)) {
                       charFreeqMap.put(leftChar, charFreeqMap.get(leftChar) + 1);

                       if (charFreeqMap.get(leftChar) > 0) {
                           matches--; 
                       }
                   }

                   windowStart++; 
               }
           }

           return totalMatches;
    }

    private static boolean isAMatch(int start, String s, String pattern) {

        for (int i = start; i < pattern.length() - start; i++) {
            if (s.charAt(i) != pattern.charAt(i)) return false; 
        }

        return true; 
    }

    public static void main(String[] args) {
        // int[] msa = {2,1,5,1,3,2};
        // int[] msa2 = {2, 3, 4, 1, 5};
        // System.out.println(maxSubArray(msa, 3));
        // System.out.println(maxSubArray(msa2, 2));

        // int[] lsa = {2, 1, 5, 2, 8};
        // System.out.println(smallestContSubarray(lsa, 7));

        //System.out.println(substringKdistinctChars("cbbebi", 3));

         // 0 0 0 0 0 
         // 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 

        //char[] a = {'A', 'B', 'C', 'B', 'B', 'C'};
        //System.out.println(fruitToBasket(a));

        char[] unChar = {'X', 'Y', 'Z'};
        String de = "xyyzyzyx";

        System.out.println(stringMatchPattern("onionionsponions", "onions"));
    }
}
