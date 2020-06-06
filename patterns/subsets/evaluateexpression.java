package algorithms; 

/**
Given an expression containing digits and operations (+, -, *), find all possible ways in which the expression can be evaluated by grouping the numbers and operators using parentheses.
E.g. 1*2+3 
Runtime O(N*2^N) 
Space O(N*2^N)
**/ 
import java.util.*;

class EvaluateExpression {
  public static List<Integer> diffWaysToEvaluateExpression(String input) {
    List<Integer> result = new ArrayList<>();

    Map<String, List<Integer>> memo = new HashMap<String, List<Integer>>();

    //Break the expressin into two halves when you get to a non digit character 
    // Recursively find the result of the halves as soon as we have a result
    // We will have a result when ever we get a half that does not contain an operand 

    if (!input.contains("+") && !input.contains("-") && !input.contains("*")) {
      // Adds input whenever we have digits without operands 
      result.add(Integer.parseInt(input));
    } else {

      if (memo.containsKey(input)) {
        // Memoiztation -> Rduce StackOverflow vulnerability by reusing already existing stack 
        // This come at a space cost due to use of HashMap. 
        return memo.get(input);
      } else {
        for (int i = 0; i < input.length(); i++) {
        if (!Character.isDigit(input.charAt(i))) {
          char currentChar = input.charAt(i);

          List<Integer> leftPart = diffWaysToEvaluateExpression(input.substring(0, i));
          List<Integer> rightPart = diffWaysToEvaluateExpression(input.substring(i+1));

          for (int n: leftPart) {
            for (int m: rightPart) {
              if (currentChar == '*') {
                result.add(n * m);
              } else if (currentChar == '-') {
                result.add(n - m);
              } else if (currentChar == '+') {
                result.add(n + m);
              }
            }
          }
        }
       }
      }
    }
    memo.put(input, result);
    return result;
  }

  public static void main(String[] args) {
    List<Integer> result = EvaluateExpression.diffWaysToEvaluateExpression("1+2*3");
    System.out.println("Expression evaluations: " + result);

    result = EvaluateExpression.diffWaysToEvaluateExpression("2*3-4-5");
    System.out.println("Expression evaluations: " + result);
  }
}
