package algorithms;

//Negative numbers are to b given good consideration in solving this type of problems 

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class HighestProductOf3 {

    public static int highestProductOf3(int[] intArray) {
        
        if (intArray.length < 3) {
            
            throw new IllegalArgumentException("Array must be 3");
        }
        // calculate the highest product of three numbers
        int max = Integer.MIN_VALUE;
        int min = Integer.MIN_VALUE;
        int mid = Integer.MIN_VALUE;
        
        int firstBestNeagative = 0;
        int secondBestNegative = 0;
        
        for (int i = 0; i < intArray.length; i++) {
            
            int num = intArray[i];
            
            if (num > max) {
                
                int maxTemp = max;
                int midTemp = mid;
                
                max = num;
                mid = maxTemp;
                min = midTemp;
                
            } else if (num > mid) {
                
                int midTemp = mid;
                
                mid = num;
                min = midTemp;
            } else if (num > min) {
                min = num;
            } 
            
            if (num < firstBestNeagative) {
                int firstBNTemp = firstBestNeagative;
                firstBestNeagative = num;
                secondBestNegative = firstBNTemp;
            } else if (num < secondBestNegative) {
                secondBestNegative = num;
            }
        }
        
        return Math.max((max*mid*min), max * firstBestNeagative * secondBestNegative);
    }
    
    //ICake's Solution 
  /*    public static int highestProductOf3(int[] arrayOfInts) {

    if (arrayOfInts.length < 3) {
        throw new IllegalArgumentException("Less than 3 items!");
    }

    // we're going to start at the 3rd item (at index 2)
    // so pre-populate highests and lowests based on the first 2 items.
    // we could also start these as null and check below if they're set
    // but this is arguably cleaner
    int highest = Math.max(arrayOfInts[0], arrayOfInts[1]);
    int lowest  = Math.min(arrayOfInts[0], arrayOfInts[1]);

    int highestProductOf2 = arrayOfInts[0] * arrayOfInts[1];
    int lowestProductOf2  = arrayOfInts[0] * arrayOfInts[1];

    // except this one--we pre-populate it for the first *3* items.
    // this means in our first pass it'll check against itself, which is fine.
    int highestProductOf3 = arrayOfInts[0] * arrayOfInts[1] * arrayOfInts[2];

    // walk through items, starting at index 2
    for (int i = 2; i < arrayOfInts.length; i++) {
        int current = arrayOfInts[i];

        // do we have a new highest product of 3?
        // it's either the current highest,
        // or the current times the highest product of two
        // or the current times the lowest product of two
        highestProductOf3 = Math.max(Math.max(
            highestProductOf3,
            current * highestProductOf2),
            current * lowestProductOf2);

        // do we have a new highest product of two?
        highestProductOf2 = Math.max(Math.max(
            highestProductOf2,
            current * highest),
            current * lowest);

        // do we have a new lowest product of two?
        lowestProductOf2 = Math.min(Math.min(
            lowestProductOf2,
            current * highest),
            current * lowest);

        // do we have a new highest?
        highest = Math.max(highest, current);

        // do we have a new lowest?
        lowest = Math.min(lowest, current);
    }

    return highestProductOf3;
}*/

    // tests

    @Test
    public void shortArrayTest() {
        final int actual = highestProductOf3(new int[] {1, 2, 3, 4});
        final int expected = 24;
        assertEquals(expected, actual);
    }

    @Test
    public void longerArrayTest() {
        final int actual = highestProductOf3(new int[] {6, 1, 3, 5, 7, 8, 2});
        final int expected = 336;
        assertEquals(expected, actual);
    }

    @Test
    public void arrayHasOneNegativeTest() {
        final int actual = highestProductOf3(new int[] {-5, 4, 8, 2, 3});
        final int expected = 96;
        assertEquals(expected, actual);
    }

    @Test
    public void arrayHasTwoNegativesTest() {
        final int actual = highestProductOf3(new int[] {-10, 1, 3, 2, -10});
        final int expected = 300;
        assertEquals(expected, actual);
    }

    @Test
    public void arrayIsAllNegativesTest() {
        final int actual = highestProductOf3(new int[] {-5, -1, -3, -2});
        final int expected = -6;
        assertEquals(expected, actual);
    }

    @Test(expected = Exception.class)
    public void exceptionWithEmptyArrayTest() {
        highestProductOf3(new int[] {});
    }

    @Test(expected = Exception.class)
    public void exceptionWithOneNumberTest() {
        highestProductOf3(new int[] {1});
    }

    @Test(expected = Exception.class)
    public void exceptionWithTwoNumbersTest() {
        highestProductOf3(new int[] {1, 1});
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(HighestProductOf3.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}