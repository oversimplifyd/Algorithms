package algorithms; 

/**
Dutch National flag problem. 
 */
class DutchFlag {

  public static void sort(int[] arr) {

    int low = 0; 
    int high = arr.length - 1; 

    for (int i = 0; i <= high;) {

      if (arr[i] == 0) {
        swap(arr, i , low);
        i++; 
        low++;
      } else if (arr[i] == 1) {
        i++;
      } else {
        swap(arr, i, high);
        high--; 
      }
    }
  }

  private static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp; 
  }
}






import java.io.*;
import java.util.*;

class Solution {

  static String getShortestUniqueSubstring(char[] arr, String str) {
    
    int windowStart = 0; 
    int arrLength = arr.length; 
    
    int matches = 0; 
    HashMap<Character, Integer> charFreqMap = new HashMap<>(); 
    
    for (char ch : arr) {
      charFreqMap.put(ch, charFreqMap.getOrDefault(ch, 0) +1);
      // X -> 1  -> 0 
      // Y -> 1 
      // Z - 1 
    }
    
    // O(M) arr.size() space complexity
    
    //xyyzyzyx
    
    for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
      
      char rightChar = str.charAt(windowEnd);
      
      if (charFreqMap.containsKey(rightChar)) { // O(1)
        
        charFreqMap.put(ch, charFreqMap.get(ch) - 1);
        
        if (charFreqMap.get(ch) == 0) {
          matches++; 
        }
      }
      
      if (matches == arrLength) {
        return str.substring(windowStart, windowEnd+1);
      }
      
      
      if (windowEnd - windowStart + 1 == arrLength) {
        char leftChar = str.charAt(windowStart); // x
        charFreqMap.put(leftChar, charFreqMap.get(leftChar) + 1); 
        matches--; 
        
       windowStart++; // shrinks 

      }
    }
  }

  public static void main(String[] args) {

  }

}

//4 => 0, 3 
