package algoritms;

/**
Given a string, find the length of the longest substring in it with no more than K distinct characters.
 */
import java.util.*;

class LongestSubstringKDistinct {
  public static int findLength(String str, int k) {
    int max = 0; 
    int windowStart = 0; 
    Map<Character, Integer> characterFrequencyMap = new HashMap<Character, Integer>(); 
    
    for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
      
      char currentChar = str.charAt(windowEnd);
      characterFrequencyMap.put(currentChar, characterFrequencyMap.getOrDefault(currentChar, 0) +1);

      while (characterFrequencyMap.size() > k) {
        //max = Math.max(max, windowEnd - windowStart);
        max = Math.max(max, windowEnd - windowStart);
        char leftChar = str.charAt(windowStart);

        characterFrequencyMap.put(leftChar, characterFrequencyMap.get(leftChar) - 1);
        if (characterFrequencyMap.get(leftChar) == 0) {
          characterFrequencyMap.remove(leftChar);
        }

        windowStart++;
      }
    }
    return max;
  }
}
