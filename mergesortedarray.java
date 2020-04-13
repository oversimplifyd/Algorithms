package algorithms;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class MergeSortedArray {

    public static int[] mergeArrays(int[] myArray, int[] alicesArray) {

        // combine the sorted arrays into one large sorted array
        if (myArray.length == 0 && alicesArray.length == 0)  {
            return new int[] {};
        }
        
        if (myArray.length == 0) {
            return alicesArray;
        } else if (alicesArray.length ==0) {
            return myArray;
        } else {
            
            int[] mergedArray = new int[myArray.length + alicesArray.length];
            
            int myArrayPointer = 0; 
            int alicesArrayPointer = 0; 
            
            int mergedArrayIndex = 0;
            
            while (mergedArrayIndex < mergedArray.length) {
                
                if (myArrayPointer < myArray.length 
                    && ((alicesArrayPointer < alicesArray.length && myArray[myArrayPointer] < alicesArray[alicesArrayPointer]) 
                        || alicesArrayPointer == alicesArray.length )) {
                            mergedArray[mergedArrayIndex] = myArray[myArrayPointer];
                            myArrayPointer++;
                } else {
                    mergedArray[mergedArrayIndex] = alicesArray[alicesArrayPointer];
                    alicesArrayPointer++;
                }
                
                  mergedArrayIndex++;
            }
            
            return mergedArray;
        }
    }

    // tests

    @Test
    public void bothArraysAreEmptyTest() {
        final int[] myArray = {};
        final int[] alicesArray = {};
        final int[] expected = {};
        final int[] actual = mergeArrays(myArray, alicesArray);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void firstArrayIsEmptyTest() {
        final int[] myArray = {};
        final int[] alicesArray = {1, 2, 3};
        final int[] expected = {1, 2, 3};
        final int[] actual = mergeArrays(myArray, alicesArray);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void secondArrayIsEmptyTest() {
        final int[] myArray = {5, 6, 7};
        final int[] alicesArray = {};
        final int[] expected = {5, 6, 7};
        final int[] actual = mergeArrays(myArray, alicesArray);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void bothArraysHaveSomeNumbersTest() {
        final int[] myArray = {2, 4, 6};
        final int[] alicesArray = {1, 3, 7};
        final int[] expected = {1, 2, 3, 4, 6, 7};
        final int[] actual = mergeArrays(myArray, alicesArray);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void arraysAreDifferentLengthsTest() {
        final int[] myArray = {2, 4, 6, 8};
        final int[] alicesArray = {1, 7};
        final int[] expected = {1, 2, 4, 6, 7, 8};
        final int[] actual = mergeArrays(myArray, alicesArray);
        assertArrayEquals(expected, actual);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(MergeSortedArray.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}