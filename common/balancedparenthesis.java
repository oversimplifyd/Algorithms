/**
You're working with an intern that keeps coming to you with JavaScript code that won't run because the braces, brackets, and parentheses are off. To save you both some time, you decide to write a braces/brackets/parentheses validator.

Let's say:

'(', '{', '[' are called "openers."
')', '}', ']' are called "closers."
Write an efficient method that tells us whether or not an input string's openers and closers are properly nested.

Examples:

"{ [ ] ( ) }" should return true
"{ [ ( ] ) }" should return false
"{ [ }" should return false
 */
package algorithms; 

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.ArrayDeque;
import java.util.Deque;

import java.util.Map;
import java.util.HashMap;

import static org.junit.Assert.*;

public class BalancedParenthesis {

    public static boolean isValid(String code) {
        
        Deque<Character> stack = new ArrayDeque<Character>();
        
        Map<Character, Character> matches = new HashMap<Character, Character>();
        
        matches.put(']', '[');
        matches.put(')', '(');
        matches.put('}', '{');
        
        int codeLength = code.length();
        
        for (int i = 0; i < codeLength; i++) {
            
            char currentChar = code.charAt(i);
            
            if (currentChar == '{' || currentChar == '[' || currentChar == '(') {
                stack.push(currentChar);
            } else if (currentChar == '}' || currentChar == ']' || currentChar == ')') {
                
                if (stack.isEmpty() || stack.pop() != matches.get(currentChar)) return false;
            }
        }
        
       return stack.isEmpty();
    }
    
    //ICake's Solution 
    /*public static boolean isValid(String code) {
    
        Map<Character, Character> openersToClosers = new HashMap<>();
        openersToClosers.put('(', ')');
        openersToClosers.put('[', ']');
        openersToClosers.put('{', '}');
    
        Set<Character> openers = openersToClosers.keySet();
        Set<Character> closers = new HashSet<>(openersToClosers.values());
    
        Deque<Character> openersStack = new ArrayDeque<>();
    
        for (char c : code.toCharArray()) {
            if (openers.contains(c)) {
                openersStack.push(c);
            } else if (closers.contains(c)) {
                if (openersStack.isEmpty()) {
                    return false;
                } else {
                    char lastUnclosedOpener = openersStack.pop();
    
                    // if this closer doesn't correspond to the most recently
                    // seen unclosed opener, short-circuit, returning false
                    if (openersToClosers.get(lastUnclosedOpener) != c) {
                        return false;
                    }
                }
            }
        }
        return openersStack.isEmpty();
    }*/

    // tests

    @Test
    public void validShortCodeTest() {
        final boolean result = isValid("()");
        assertTrue(result);
    }

    @Test
    public void validLongerCodeTest() {
        final boolean result = isValid("([]{[]})[]{{}()}");
        assertTrue(result);
    }

    @Test
    public void mismatchedOpenerAndCloserTest() {
        final boolean result = isValid("([][]}");
        assertFalse(result);
    }

    @Test
    public void interleavedOpenersAndClosersTest() {
        final boolean result = isValid("([)]");
        assertFalse(result);
    }

    @Test
    public void missingCloserTest() {
        final boolean result = isValid("[[]()");
        assertFalse(result);
    }

    @Test
    public void extraCloserTest() {
        final boolean result = isValid("[[]]())");
        assertFalse(result);
    }

    @Test
    public void emptyStringTest() {
        final boolean result = isValid("");
        assertTrue(result);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(BalancedParenthesis.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}