package algorithms; 

import java.util.*;

/**
This probem is siilar to finding longest substring with at most 2 distinct characters 

Problem Statement 
Given an array of characters where each character represents a fruit tree, you are given two baskets and your goal is to put maximum number of fruits in each basket. The only restriction is that each basket can have only one type of fruit.

You can start with any tree, but once you have started you can’t skip a tree. You will pick one fruit from each tree until you cannot, i.e., you will stop when you have to pick from a third fruit type.

Write a function to return the maximum number of fruits in both the baskets.
Time -> O(N)
Space -> O(1) 
 */
class MaxFruitCountOf2Types {
  public static int findLength(char[] arr) {
    int windowStart = 0; 
    int max = 0;

    HashMap<Character, Integer> treeFrequencyMap = new HashMap<Character, Integer>();

    for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {

      char currentChar = arr[windowEnd];
      treeFrequencyMap.put(currentChar, treeFrequencyMap.getOrDefault(currentChar, 0) + 1);

      while (treeFrequencyMap.size() > 2) {
        char leftChar = arr[windowStart];
        treeFrequencyMap.put(leftChar, treeFrequencyMap.get(leftChar) -1);
        if (treeFrequencyMap.get(leftChar) == 0) {
          treeFrequencyMap.remove(leftChar);
        }
        windowStart++;
      }
      max = (windowEnd - windowStart) + 1;
    }

    return max;
  }
}
