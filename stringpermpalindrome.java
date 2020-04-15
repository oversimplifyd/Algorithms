import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.Set;
import java.util.HashSet;

import static org.junit.Assert.*;

public class StringPermPalindrome {

    public static boolean hasPalindromePermutation(String theString) {

        // check if any permutation of the input is a palindrome
        
        Set<Character> uniqueChars = new HashSet<>();
        
        int stringLength = theString.length();
        
        for (int i = 0; i < stringLength; i++) {
            if (uniqueChars.contains(theString.charAt(i))) {
                uniqueChars.remove(theString.charAt(i));
            } else {
                uniqueChars.add(theString.charAt(i));
            }
        }
        
        if ((stringLength % 2 == 0 && uniqueChars.size() == 0) 
            || (stringLength % 2 > 0 && uniqueChars.size() == 1)) {
            return true;
        }
        
        return false;
    }

    // tests

    @Test
    public void permutationWithOddNumberOfCharsTest() {
        final boolean result = hasPalindromePermutation("aabcbcd");
        assertTrue(result);
    }

    @Test
    public void permutationWithEvenNumberOfCharsTest() {
        final boolean result = hasPalindromePermutation("aabccbdd");
        assertTrue(result);
    }

    @Test
    public void noPermutationWithOddNumberOfChars() {
        final boolean result = hasPalindromePermutation("aabcd");
        assertFalse(result);
    }

    @Test
    public void noPermutationWithEvenNumberOfCharsTest() {
        final boolean result = hasPalindromePermutation("aabbcd");
        assertFalse(result);
    }

    @Test
    public void emptyStringTest() {
        final boolean result = hasPalindromePermutation("");
        assertTrue(result);
    }

    @Test
    public void oneCharacterStringTest() {
        final boolean result = hasPalindromePermutation("a");
        assertTrue(result);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(StringPermPalindrome.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}