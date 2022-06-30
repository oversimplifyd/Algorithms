package algorithms;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class Solution {

    public static void reverseWords(char[] message) {
        
       

        reverseInplace(message, 0, message.length - 1);
        
        int startIndex = 0;
        
        for (int i = 0; i < message.length; i++) {
            if (message[i] == ' ') {
                reverseInplace(message, startIndex, i - 1);
                startIndex = i + 1;
            } else if (i == message.length - 1) {
                reverseInplace(message, startIndex, i);
            }
        }
    }
    
    public static void reverseInplace(char[] m, int startIndex, int endIndex) {
        // decode the message by reversing the words
        int leftPointer = startIndex;
        int rightPointer = endIndex;
        
        while (leftPointer < rightPointer) {
            char temp = m[leftPointer];
            
            m[leftPointer] = m[rightPointer];
            m[rightPointer] = temp; 
            
            leftPointer++;
            rightPointer--;
        }
    }

    // tests

    @Test
    public void oneWordTest() {
        final char[] expected = "vault".toCharArray();
        final char[] actual = "vault".toCharArray();
        reverseWords(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void twoWordsTest() {
        final char[] expected = "cake thief".toCharArray();
        final char[] actual = "thief cake".toCharArray();
        reverseWords(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void threeWordsTest() {
        final char[] expected = "get another one".toCharArray();
        final char[] actual = "one another get".toCharArray();
        reverseWords(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void multipleWordsSameLengthTest() {
        final char[] expected = "the cat ate the rat".toCharArray();
        final char[] actual = "rat the ate cat the".toCharArray();
        reverseWords(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void multipleWordsDifferentLengthsTest() {
        final char[] expected = "chocolate bundt cake is yummy".toCharArray();
        final char[] actual = "yummy is cake bundt chocolate".toCharArray();
        reverseWords(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void emptyStringTest() {
        final char[] expected = "".toCharArray();
        final char[] actual = "".toCharArray();
        reverseWords(actual);
        assertArrayEquals(expected, actual);
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