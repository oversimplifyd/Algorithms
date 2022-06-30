package algorithms;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class SortedArrayRoation {

    public static int findRotationPoint(String[] words) {
        
        char startChar = words[0].charAt(0);
        int startCharVal = Character.getNumericValue(Character.toLowerCase(startChar));
        
        // find the rotation point in the array
        int floorIndex = -1;
        int ceilIndex = words.length; 
        
        while ((floorIndex + 1) < ceilIndex) {
            
            int distance = (ceilIndex - floorIndex) / 2;
            int guessIndex = distance + floorIndex; 
            
            int prevIndex = guessIndex - 1;
            
            if (prevIndex < 0) prevIndex = 0;
            
            char val1 = words[guessIndex].charAt(0);
            int charVal = Character.getNumericValue(Character.toLowerCase(val1));
            
            char val2 = words[prevIndex].charAt(0);
            int prevCharVal = Character.getNumericValue(Character.toLowerCase(val2));
            
            if (charVal < startCharVal && prevCharVal >= startCharVal || (charVal == startCharVal && words[guessIndex].compareTo(words[0]) < 0)) {
                return guessIndex;
            } else if (charVal < startCharVal && prevCharVal < startCharVal) {
               ceilIndex = guessIndex;
            } else {
                 floorIndex = guessIndex;
            }
        }
        
        return  0;
    }

    // tests

    @Test
    public void smallArrayTest() {
        final int actual = findRotationPoint(new String[] {"cape", "cake"});
        final int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void mediumArrayTest() {
        final int actual = findRotationPoint(new String[] {"grape", "orange", "plum",
            "radish", "apple"});
        final int expected = 4;
        assertEquals(expected, actual);
    }

    @Test
    public void largeArrayTest() {
        final int actual = findRotationPoint(
            new String[] {"ptolemaic", "retrograde", "supplant", "undulate", "xenoepist",
            "asymptote", "babka", "banoffee", "engender", "karpatka", "othellolagkage"});
        final int expected = 5;
        assertEquals(expected, actual);
    }

    @Test
    public void possiblyMissingEdgeCaseTest() {
        // are we missing any edge cases?
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(SortedArrayRoation.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}