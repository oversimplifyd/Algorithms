package algorithms; 

/**
Given a string and a pattern, find out if the string contains any permutation of the pattern.

Permutation is defined as the re-arranging of the characters of the string. For example, “abc” has the following six permutations:
 */
import java.util.*;

class StringPermutation {

    //Brillliant! 
    public static boolean findPermutation(String str, String pattern) {
    int windowStart = 0, matched = 0;
    Map<Character, Integer> charFrequencyMap = new HashMap<>();
    for (char chr : pattern.toCharArray())
      charFrequencyMap.put(chr, charFrequencyMap.getOrDefault(chr, 0) + 1);

    // our goal is to match all the characters from the 'charFrequencyMap' with the current window
    // try to extend the range [windowStart, windowEnd]
    for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
      char rightChar = str.charAt(windowEnd);
      if (charFrequencyMap.containsKey(rightChar)) {
        // decrement the frequency of the matched character
        charFrequencyMap.put(rightChar, charFrequencyMap.get(rightChar) - 1);
        if (charFrequencyMap.get(rightChar) == 0) // character is completely matched
          matched++;
      }

      if (matched == charFrequencyMap.size())
        return true;

      if (windowEnd >= pattern.length() - 1) { // shrink the window by one character
        char leftChar = str.charAt(windowStart++);
        if (charFrequencyMap.containsKey(leftChar)) {
          if (charFrequencyMap.get(leftChar) == 0)
            matched--; // before putting the character back, decrement the matched count
          // put the character back for matching
          charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) + 1);
        }
      }
    }

    return false;
  }

  public static void main(String[] args) {
    System.out.println("Permutation exist: " + StringPermutation.findPermutation("oidbcaf", "abc"));
    System.out.println("Permutation exist: " + StringPermutation.findPermutation("odicf", "dc"));
    System.out.println("Permutation exist: " + StringPermutation.findPermutation("bcdxabcdy", "bcdyabcdx"));
    System.out.println("Permutation exist: " + StringPermutation.findPermutation("aaacb", "abc"));
  }
    //Naive Solution -> Brute force 
//   public static boolean findPermutation(String str, String pattern) {

//     int windowStart = 0; 
//     int currentCount = 0; 

//     for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
//       currentCount++; 

//       if (currentCount == pattern.length()) {
//         if (isPermutationOf(pattern, str, windowStart, windowEnd)) {
//           return true; 
//         }
//         currentCount--;
//         windowStart++;
//       }
//     }
//     return false;
//   }

//   private static boolean isPermutationOf(String pattern, String checkString, int start, int end) {

//     for (int i = start; i < end + 1; i++) {
//       if (pattern.indexOf(checkString.charAt(i)) < 0) {
//         return false; 
//       }
//     }
    
//     return true; 
//   }
}
