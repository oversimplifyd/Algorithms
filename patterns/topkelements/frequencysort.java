package algorithms; 

/**
Given a string, sort it based on the decreasing frequency of its characters.

 */
import java.util.*;

class FrequencySort {

  public static String sortCharacterByFrequency(String str) {
    
    Map<Character, Integer> charMap = new HashMap<>(); 

    for (int i = 0; i < str.length(); i++) {   // O(N) 
      charMap.put(str.charAt(i), charMap.getOrDefault(str.charAt(i), 0) + 1);
    }

    PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<Map.Entry<Character, Integer>>(charMap.size(), (a, b) -> b.getValue() - a.getValue());

    maxHeap.addAll(charMap.entrySet()); // // (ONLogN)
    
    // for (Map.Entry<Character, Integer> entry: charMap.entrySet()) { 
    //   maxHeap.add(entry); 
    // }

    //0(NLogN * K) > where K is the chracter with the highest freq. 
    // Since frequency is going to be a constant value, our complexity is O(NLogN)
    StringBuilder sortedString = new StringBuilder(str.length());
    while (!maxHeap.isEmpty()) { 
      Map.Entry<Character, Integer> en = maxHeap.poll();
      int keySize = (int) en.getValue();
      for (int i = 0; i < keySize; i++) {
        sortedString.append(en.getKey());
      }
    }

    return sortedString.toString();
  }

  public static void main(String[] args) {
    String result = FrequencySort.sortCharacterByFrequency("Programming");
    System.out.println("Here is the given string after sorting characters by frequency: " + result);

    result = FrequencySort.sortCharacterByFrequency("abcbab");
    System.out.println("Here is the given string after sorting characters by frequency: " + result);
  }
}
