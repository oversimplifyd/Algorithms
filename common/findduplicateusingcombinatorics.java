package algorithms;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class Solution {

    public static int findRepeat(int[] numbers) {
        
        if (numbers.length < 2) {
            throw new IllegalArgumentException("Finding duplicate requires at least two numbers");
        }
        // find the number that appears twice
        int n = numbers.length - 1; 
        int triangularSum =  n * (n + 1) / 2;
        
        int arraySum = 0; 
        
        for(int item : numbers) {
            arraySum += item;
        }
        
        return arraySum - triangularSum; 
    }
    
    // tests

    @Test
    public void shortArrayTest() {
        final int[] numbers = {1, 2, 1};
        final int expected = 1;
        final int actual = findRepeat(numbers);
        assertEquals(expected, actual);
    }

    @Test
    public void mediumArrayTest() {
        final int[] numbers = {4, 1, 3, 4, 2};
        final int expected = 4;
        final int actual = findRepeat(numbers);
        assertEquals(expected, actual);
    }

    @Test
    public void longArrayTest() {
        final int[] numbers = {1, 5, 9, 7, 2, 6, 3, 8, 2, 4};
        final int expected = 2;
        final int actual = findRepeat(numbers);
        assertEquals(expected, actual);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(Solution.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}