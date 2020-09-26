/**
Write a method fib() that takes an integer nn and returns the nnth Fibonacci ↴ number.

Let's say our Fibonacci series is 0-indexed and starts with 0. So:
 */
 
package algorithms;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class FibSequence {
        
    //BottomUp Approach 
    public static int fib(int n) {
        
        if (n < 0) throw new IllegalArgumentException("Invalid Input");
        
        if (n == 1) return 1;
        if (n == 0) return 0;
        
        int a = 0; 
        int b = 1; 
        
        for (int i = 2; i <= n; i++) {
            int aTemp = a; 
            int bTemp = b; 
            
            a = bTemp;
            b = aTemp + bTemp;
        }
        
        return b;
    }
    
    // Memozied Rcursive Approach 
    /*public static int fib(int n) {
        
        if (n < 0) throw new IllegalArgumentException("Invalid Input");
        
        if (n == 1) return 1;
        if (n == 0) return 0;
        
        if (resultSet.containsKey(n)) {
            return resultSet.get(n); 
        }
        
        int result = fib(n - 1) + fib(n - 2);
        
        resultSet.put(n, result);
        
        return result;
    }*/
    
    // tests

    @Test
    public void zerothFibonacciTest() {
        final int actual = fib(0);
        final int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void firstFibonacciTest() {
        final int actual = fib(1);
        final int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void secondFibonacciTest() {
        final int actual = fib(2);
        final int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void thirdFibonacciTest() {
        final int actual = fib(3);
        final int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    public void fifthFibonacciTest() {
        final int actual = fib(5);
        final int expected = 5;
        assertEquals(expected, actual);
    }

    @Test
    public void tenthFibonacciTest() {
        final int actual = fib(10);
        final int expected = 55;
        assertEquals(expected, actual);
    }

    @Test(expected = Exception.class)
    public void negativeFibonacciTest() {
        fib(-1);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(FibSequence.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}