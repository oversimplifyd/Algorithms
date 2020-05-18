import java.util.*;

class StringAnagrams {
  public static List<Integer> findStringAnagrams(String str, String pattern) {
    List<Integer> resultIndices = new ArrayList<Integer>();
    HashMap<Character, Integer> patternFreq = getPatternFreq(pattern);

    int windowStart = 0; 
    int matches = 0; 

    for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
      char rightChar = str.charAt(windowEnd); 

      if (patternFreq.containsKey(rightChar)) {
        //reduce the freuency of thi char seen so far in pattern 
        patternFreq.put(rightChar, patternFreq.get(rightChar) -1);

        if (patternFreq.get(rightChar) == 0) {
          matches++;
        }
      }
      
      if (matches == patternFreq.size()) {
        //there is a match 
        resultIndices.add(windowStart);
      }

      /// Continuously remove leftmost character as soon as we have the exact pattern length and there is no match 
      if (windowEnd >= pattern.length() - 1) {
        char leftChar = str.charAt(windowStart);
        if (patternFreq.containsKey(leftChar)) {
          // if the character to shrink is pat of the former match 
          // Well it's no longer part, reduce match and restore its freq. 
          if (patternFreq.get(leftChar) == 0)
            matches--;
          
          patternFreq.put(leftChar, patternFreq.get(leftChar) + 1);
        }

        windowStart++;  
      }
    }

    return resultIndices;
  }

  private static HashMap<Character, Integer> getPatternFreq(String pattern) {
    HashMap<Character, Integer> chFreq = new HashMap<Character, Integer>();
    for (char ch: pattern.toCharArray()) {
      chFreq.put(ch, chFreq.getOrDefault(ch, 0) + 1);
    }
    return chFreq;
  }
}
