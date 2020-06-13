package algorithms; 

/**
Given a string, find if its letters can be rearranged in such a way that no two same characters come next to each other.

O(N * LogN)
 */
import java.util.*;

class RearrangeString {

  public static String rearrangeString(String str) {

    Map<Character, Integer> charFreqMap = new HashMap<>();
    PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<Map.Entry<Character, Integer>>((a, b) -> b.getValue() - a.getValue());
    Queue<Map.Entry<Character, Integer>> letterQueue = new LinkedList<>();

    for (int i = 0; i < str.length(); i++) { // O(N)
      charFreqMap.put(str.charAt(i), charFreqMap.getOrDefault(str.charAt(i), 0) + 1);
    }

    for (Map.Entry<Character, Integer> entry: charFreqMap.entrySet()) {  //O(N * LogN)
      maxHeap.offer(entry);
    }

    StringBuilder buildString = new StringBuilder(str.length());

    while (!maxHeap.isEmpty()) { // O(N * LogN)
      Map.Entry<Character, Integer> currentEntry = maxHeap.poll();
      buildString.append(currentEntry.getKey());
      currentEntry.setValue(currentEntry.getValue() - 1);
      letterQueue.add(currentEntry);
    }

    while (!letterQueue.isEmpty()) {  // O(N) 
      Map.Entry<Character, Integer> currentEntry = letterQueue.poll();

      if (currentEntry.getValue() > 0) {
        if (buildString.charAt(buildString.length() - 1) == currentEntry.getKey()) 
          return "";
         
         buildString.append(currentEntry.getKey());
         currentEntry.setValue(currentEntry.getValue() - 1);
         letterQueue.add(currentEntry);
      }
    }

    return buildString.toString();
  }

  public static void main(String[] args) {
    System.out.println("Rearranged string: " + RearrangeString.rearrangeString("aappp"));
    System.out.println("Rearranged string: " + RearrangeString.rearrangeString("Programming"));
    System.out.println("Rearranged string: " + RearrangeString.rearrangeString("aapa"));
  }
}
