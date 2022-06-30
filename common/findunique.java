package algorithms;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class FindUnique {

    public static int findUniqueDeliveryId(int[] deliveryIds) {
        
        int exOred = 0;
        
        for (int id : deliveryIds) {
            exOred ^= id;
        }
        
        return exOred;
    }
    
    // O(n) time 
    // O(n) space 
    public static int findUniqueDeliveryId2(int[] deliveryIds) {

        // find the one unique ID in the array
        int[] droneTracker = new int[101];
        
        for (int i = 0; i < deliveryIds.length; i++) {
            if (droneTracker[deliveryIds[i]] == 0) {
                droneTracker[deliveryIds[i]] = 1;
            } else {
                droneTracker[deliveryIds[i]] = 0; 
            }
        }
        
        for (int i = 0; i < droneTracker.length; i++) {
            if (droneTracker[i] == 1) return i; 
        }
        
        return 0;
    }
    
    // tests

    @Test
    public void oneDroneTest() {
        final int expected = 1;
        final int actual = findUniqueDeliveryId(new int[] {1});
        assertEquals(expected, actual);
    }

    @Test
    public void uniqueIdComesFirstTest() {
        final int expected = 1;
        final int actual = findUniqueDeliveryId(new int[] {1, 2, 2});
        assertEquals(expected, actual);
    }

    @Test
    public void uniqueIdComesLastTest() {
        final int expected = 1;
        final int actual = findUniqueDeliveryId(new int[] {3, 3, 2, 2, 1});
        assertEquals(expected, actual);
    }

    @Test
    public void uniqueIdInTheMiddleTest() {
        final int expected = 1;
        final int actual = findUniqueDeliveryId(new int[] {3, 2, 1, 2, 3});
        assertEquals(expected, actual);
    }

    @Test
    public void manyDronesTest() {
        final int expected = 8;
        final int actual = findUniqueDeliveryId(new int[] {2, 5, 4, 8, 6, 3, 1, 4, 2, 3, 6, 5, 1});
        assertEquals(expected, actual);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(FindUnique.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}