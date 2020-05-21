package algorithms; 

/**
Given an array of unique characters arr and a string str, Implement a function getShortestUniqueSubstring that finds the smallest substring of str containing all the characters in arr. Return "" (empty string) if such a substring doesn’t exist.

Come up with an asymptotically optimal solution and analyze the time and space complexities.
 */

import java.util.*;

class Solution {

  static String getShortestUniqueSubstring(char[] arr, String str) {
    
    HashMap<Character, Integer> matchesMap = new HashMap<Character, Integer>();
    
    for(char ch: arr) {
      matchesMap.put(ch, matchesMap.getOrDefault(ch, 0)+1);
    }
    
    int matches = 0; 
    int min = Integer.MAX_VALUE; 
    int firstIndex = 0; 
    int lastIndex = 0; 
    
    int windowStart = 0; 
    
    // xyyzyzyx
    for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
      
      char rightChar = str.charAt(windowEnd);
      
      if (matchesMap.containsKey(rightChar)) {
        matchesMap.put(rightChar, matchesMap.get(rightChar) - 1);
        if (matchesMap.get(rightChar) == 0){
          matches++; 
        }
      }
      
      while (matches == arr.length) {
        
        System.out.println(str.substring(windowStart, windowEnd+1));
        if (min >= windowEnd - windowStart + 1) {
          min = windowEnd - windowStart + 1; 
          firstIndex = windowStart; 
          lastIndex = windowEnd; 
          
          if (min == arr.length) {
            return str.substring(windowStart, windowEnd+1);
          }
        }
        
        char leftChar = str.charAt(windowStart);
        
        if (matchesMap.containsKey(leftChar)) {
          matchesMap.put(leftChar, matchesMap.get(leftChar) +1);
          
          if (matchesMap.get(leftChar) > 0) {
            matches--;
          }
        }
        
        windowStart++; 
      } 
    }
    
    return lastIndex > 0 ? str.substring(firstIndex, lastIndex+1) : "";
    
  }

  public static void main(String[] args) {
    
    String testinput = "ADOBECODE";
    //System.out.println(getShortestUniqueSubstring(new char[]{'A','B','C'}, testinput));
  }
}
