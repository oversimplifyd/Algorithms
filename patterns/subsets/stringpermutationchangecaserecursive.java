package algorithms; 

/**
Given a string, find all of its permutations preserving the character sequence but changing case.
Runtime -> O(N * 2^N0 where N is the length of the string 
Space -> O(N * 2^N)  -> where N is the length of the string )
 */
import java.util.*;

class LetterCaseStringPermutation {

  // Recursive solution 
  public static List<String> findLetterCaseStringPermutations(String str) {
    List<String> permutations = new ArrayList<>();
    permutations.add(str);
    generatePermutations(str, 0, permutations);

    return permutations;
  }

  private static void generatePermutations(String str, int index, List<String> result) {

    if (index <= str.length() - 1) {

      if (!Character.isDigit(str.charAt(index))) {
        int currentResultSize = result.size();
          for (int i = 0; i < currentResultSize; i++) {
          String currentPermutation = result.get(i);
          char[] currentPermutationCharArray = currentPermutation.toCharArray();

          char currentChar = currentPermutationCharArray[index];

          if (Character.isLowerCase(currentChar)) {
            currentPermutationCharArray[index] = Character.toUpperCase(currentPermutationCharArray[index]);
          } else {
            currentPermutationCharArray[index] = Character.toLowerCase(currentPermutationCharArray[index]);
          }

          result.add(String.valueOf(currentPermutationCharArray));
        }
      }
      generatePermutations(str, index + 1, result);      
    } 
  }

  public static void main(String[] args) {
    List<String> result = LetterCaseStringPermutation.findLetterCaseStringPermutations("ad52");
    System.out.println(" String permutations are: " + result);

    result = LetterCaseStringPermutation.findLetterCaseStringPermutations("Bb7c");
    System.out.println(" String permutations are: " + result);
  }
}
