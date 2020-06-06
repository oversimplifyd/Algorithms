package algorithms; 

/**
For a given number ‘N’, write a function to generate all combination of ‘N’ pairs of balanced parentheses.
Runtime O(2^N) 
Space O(2^N) 
 */
import java.util.*;

class GenerateParentheses {

  // Conditions: 
  // There can't be more than N open parenthesis and N close parenthesis 
  // We can only add a close parenthsis if there are less than N close parenthesis and there are  more than 0 open parenthesis and open parenthesis count is equal to close parenthesis 
  // recurse on these conditions. 
  public static List<String> generateValidParentheses(int num) {
    List<String> result = new ArrayList<String>();

    char[] perm = new char[num * 2];
    generateValidParenthesesRecursive(num, perm, 0, 0, 0, result);
    return result;
  }

  private static void generateValidParenthesesRecursive(int num, char[] currentPermutations, int index, int openParenthesis, int closeParenthesis, List<String> result) {

    if (openParenthesis == num && closeParenthesis == num) {
      result.add(String.valueOf(currentPermutations));
    } else {

      if (openParenthesis < num) {
        currentPermutations[index] = '('; // add open  sublist 
        generateValidParenthesesRecursive(num, currentPermutations, index + 1, openParenthesis + 1, closeParenthesis, result);
      } 
      
      if (closeParenthesis < num && openParenthesis > 0 && openParenthesis != closeParenthesis) {
        currentPermutations[index] = ')';
        generateValidParenthesesRecursive(num, currentPermutations, index + 1, openParenthesis, closeParenthesis + 1, result);
      }
    }
  }

  public static void main(String[] args) {
    List<String> result = GenerateParentheses.generateValidParentheses(2);
    System.out.println("All combinations of balanced parentheses are: " + result);

     result = GenerateParentheses.generateValidParentheses(3);
     System.out.println("All combinations of balanced parentheses are: " + result);
  }
}
