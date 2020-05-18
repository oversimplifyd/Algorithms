package algorithms; 

/**
Given a string and a list of words, find all the starting indices of substrings in the given string that are a concatenation of all the given words exactly once without any overlapping of words. It is given that all words are of the same length.
 */
import java.util.*;

class WordConcatenation {

    // Efficient but not Optimal 
  public static List<Integer> findWordConcatenation(String str, String[] words) {
    List<Integer> resultIndices = new ArrayList<Integer>();
    
    String firstWord = words[0];
    int wordLength = firstWord.length();
    int wordArrayLength = words.length;

    int totalAllowableWords = wordArrayLength * wordLength;

    int windowStart = 0;
    int firstMatchIndex = 0; 

    int matches = 0;
    
    HashMap<String, Integer> wordFreqMap = new HashMap<String, Integer>();

    for (String word: words) {
      wordFreqMap.put(word, wordFreqMap.getOrDefault(word, 0) + 1);
    }


    for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {

      if (windowEnd - windowStart + 1 >= wordLength) {
        String contiguousRunningChars = str.substring(windowStart, windowEnd+1);
        if (wordFreqMap.containsKey(contiguousRunningChars)) {

          wordFreqMap.put(contiguousRunningChars, wordFreqMap.get(contiguousRunningChars) - 1);
          if (wordFreqMap.get(contiguousRunningChars) == 0) {
            matches++; 
          } else {
               firstMatchIndex = windowEnd - wordLength + 1;
          }
        }

        if (matches == wordArrayLength && windowEnd - firstMatchIndex + 1 == totalAllowableWords) {
          //We have a first match 
          resultIndices.add(firstMatchIndex);

          String removeStringfromSeen = str.substring(firstMatchIndex, firstMatchIndex+3);

          if (wordFreqMap.containsKey(removeStringfromSeen)) {
            if (wordFreqMap.get(removeStringfromSeen) == 0) {
                 matches--; 
            }
            wordFreqMap.put(removeStringfromSeen, wordFreqMap.get(removeStringfromSeen) + 1);
          }

          firstMatchIndex = windowEnd - wordLength + 1;
        }

        windowStart++;
      }

    } // catcatfox cat fox 
    return resultIndices;
  }

  public static void main(String[] args) {
    List<Integer> result = findWordConcatenation("catfoxcatcatfoxcatfoxcat", new String[] { "cat", "fox" });
    System.out.println(result);
    // result = WordConcatenation.findWordConcatenation("catcatfoxfox", new String[] { "cat", "fox" });
    // System.out.println(result);
  }
}

