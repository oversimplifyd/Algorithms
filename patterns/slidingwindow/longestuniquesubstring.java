package algorithms;

import java.util.*;

class NoRepeatSubstring {
  public static int findLength(String str) {
    //HashSet<Character> uniqueCharsSeenSoFar = new HashSet<Character>();

    HashMap<Character, Integer> seenSoFar = new HashMap<Character, Integer>();
    int windowStart = 0; 
    int max = 0; 

    for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {

      char rightChar = str.charAt(windowEnd);
      if (seenSoFar.containsKey(rightChar)) {
        //This shrinks the window to exclude this character seen previously. 
        windowStart = Math.max(windowStart, seenSoFar.get(rightChar) + 1); 
      }
      seenSoFar.put(rightChar, windowEnd); //replaces, even if it already contains key 
      max = Math.max(max, windowEnd - windowStart + 1);
    }
    return max;
  }
}
