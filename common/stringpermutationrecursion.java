package algorithms;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.Set;
import java.util.HashSet; 

import static org.junit.Assert.*;

//Runtime -> 0(2^stringLength - 1) 
//Space -> O(InputLength!)
public class permuationStringRecursion {

    public static Set<String> getPermutations(String inputString) {

        // generate all permutations of the input string
        Set<String> resultSet = new HashSet<String>();
        resultSet.add(inputString);
        
        return recurseOnSwap(resultSet, inputString);
        
    }
    
    private static Set<String> recurseOnSwap(Set<String> existingSet, String input) {
        
        char[] inputStringChar = input.toCharArray();

        for (int i = 0; i < inputStringChar.length - 1; i++) {
            
            //Swap
            char temp = inputStringChar[i];
            inputStringChar[i] = inputStringChar[i+1];
            inputStringChar[i+1] = temp;
            
            String value = new String(inputStringChar);
            
            //Add permutation to hashset if it doesn't already exisst. 
            //Memoizing -> Dynamic Programming
            //Cuts down the amount of call stack needed by the recursive subroutine() 
            if (existingSet.contains(value)) {
               continue;
            }
            
            existingSet.add(value);
            
            recurseOnSwap(existingSet, value);
        }
        
        return existingSet;
    }
    
    
    // Icake's Solution 
     /* public static Set<String> getPermutations(String inputString) {

        // base case
        if (inputString.length() <= 1) {
            return new HashSet<>(Collections.singletonList(inputString));
        }
    
        String allCharsExceptLast = inputString.substring(0, inputString.length() - 1);
        char lastChar = inputString.charAt(inputString.length() - 1);
    
        // recursive call: get all possible permutations for all chars except last
        Set<String> permutationsOfAllCharsExceptLast = getPermutations(allCharsExceptLast);
    
        // put the last char in all possible positions for each of the above permutations
        Set<String> permutations = new HashSet<>();
        for (String permutationOfAllCharsExceptLast : permutationsOfAllCharsExceptLast) {
            for (int position = 0; position <= allCharsExceptLast.length(); position++) {
                String permutation = permutationOfAllCharsExceptLast.substring(0, position) + lastChar
                    + permutationOfAllCharsExceptLast.substring(position);
                permutations.add(permutation);
            }
        }
    
        return permutations;
    }*/
    
    // tests

    @Test
    public void emptyStringTest() {
        final Set<String> expected = new HashSet<>(Arrays.asList(""));
        final Set<String> actual = getPermutations("");
        assertEquals(expected, actual);
    }

    @Test
    public void oneCharacterStringTest() {
        final Set<String> expected = new HashSet<>(Arrays.asList("a"));
        final Set<String> actual = getPermutations("a");
        assertEquals(expected, actual);
    }

    @Test
    public void twoCharacterStringTest() {
        final Set<String> expected = new HashSet<>(Arrays.asList("ab", "ba"));
        final Set<String> actual = getPermutations("ab");
        assertEquals(expected, actual);
    }

    @Test
    public void threeCharacterStringTest() {
        final Set<String> expected = new HashSet<>(Arrays.asList("abc", "acb", "bac", "bca",
                                                                 "cab", "cba"));
        final Set<String> actual = getPermutations("abc");
        assertEquals(expected, actual);
    }
    
    @Test
    public void duplicateThreeCharacterStringTest() {
        final Set<String> expected = new HashSet<>(Arrays.asList("bba",
                                                                 "bab", "abb"));
        final Set<String> actual = getPermutations("abb");
        assertEquals(expected, actual);
    }
    
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(permuationStringRecursion.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}