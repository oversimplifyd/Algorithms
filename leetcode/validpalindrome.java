package algorithms; 

import java.util.*; 

class Palindrome  {

    public static boolean isPalindromeDigit(int n) {
        // Can we solve this in O(lgN) witthout converting to a string? 
        //0 x 10 + 121 % 10  = 0 + 1 
        //1 x 10 + 12 % 10 = 10 + 2 
        //12 x 10 + 1 % 10 = 120 + 1  

        // if n is negative or n ends with a 0
        if (n < 0 || (n % 10 == 0 && n != 0)) return false; 

        int revertedNumber = 0; 
        while (n > revertedNumber) {
            revertedNumber = revertedNumber * 10 + n % 10; 
            n = n / 10; 
        }

        // n == revertedNumber / 10 is when has an odd length, the middle number is't important.
        return n == revertedNumber || n == revertedNumber / 10; 
    }

    public static boolean stringIsPalindrome(String s) {
        // O(N) 
      
      int leftPointer = 0; 
      int rightPointer = s.length() - 1; 

      while (leftPointer < rightPointer) {
          if (s.charAt(leftPointer) != s.charAt(rightPointer)) return false; 
          leftPointer++;
          rightPointer--;
      }

      return true; 
    }

    public static void main(String[] args) {
        String s = "tattar    rattat";
        String s2 = "detartrated";
        System.out.println(isPalindromeDigit(12281));
    }
}
