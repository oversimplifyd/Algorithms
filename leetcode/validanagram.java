package algorithms; 

/** 
Check if string is valid anagram 
 */

import java.util.*; 

class ValidAnagram  {

    public static boolean isValid(String s, String c) {
       
       Map<Character, Integer> charFreqMap = new HashMap<>();

       for (char ch: s.toCharArray()) {
           charFreqMap.put(ch, charFreqMap.getOrDefault(ch, 0) + 1);
       }

       for (char ch : c.toCharArray()) {
           if (!charFreqMap.containsKey(ch)) {
               return false; 
           }
           charFreqMap.put(ch, charFreqMap.get(ch) - 1);
       }

       for (Map.Entry<Character, Integer> entry: charFreqMap.entrySet()) {
           if (entry.getValue() != 0) return false; 
       }

       return true; 
    }

    public static void main(String[] args) {
        String s = "rat";
        System.out.println(isValid(s, "car"));
    }
}
