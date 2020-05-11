package algorithms;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class MeanMaxMode {

    static class TempTracker {

        // fill in the TempTracker class methods below
        private int max = 0; 
        private int min = 111;
        private int total = 0;
        private int count = 0;
        
        private int[] boundedReadings = new int[111]; 

        // records a new temperature
        //This uses an ahead of time approach and thus saves  space and time. 
        public void insert(int temperature) {
            
            if (temperature > 101) {
                throw new IllegalArgumentException("Invalid Reading..");
            }
            
            boundedReadings[temperature]++; 
            //We can also keep track of the mode by getting the maxOcurence seen so far per temp and updating
            //Saves time. 
            
            max = Math.max(max, temperature);
            min = Math.min(min, temperature);
            total += temperature; 
            count++;
        }

        // returns the highest temp we've seen so far
        public int getMax() {
            return max;
        }

        // returns the lowest temp we've seen so far
        public int getMin() {
            return min;
        }

        // returns the mean of all temps we've seen so far
        public double getMean() {
            return total / count; 
        }

        // return a mode of all temps we've seen so far
        public int getMode() {
            //This was done just-in-time 
            //It can also be done ahead-of-time by simply checking against a maxOccurence see sofar in the insert() method 
            // I decided to leave this because it is O(1) time since the array is bounded 0->101 
            int maxCount = 0; 
            int mode = 0;
            
            for (int i = 0; i < boundedReadings.length; i++) {
                if (boundedReadings[i] > maxCount) {
                    mode = i;
                }
            }
            
            return mode; 
        }
    }

    // tests

    @Test
    public void temperatureTrackerTest() {
        final double precision = 1e-6;

        final TempTracker t = new TempTracker();

        t.insert(50);
        assertEquals("step 1: max:", 50, t.getMax());
        assertEquals("step 1: min:", 50, t.getMin());
        assertEquals("step 1: mean:", 50.0, t.getMean(), precision);
        assertEquals("step 3: mode:", 50, t.getMode());

        t.insert(80);
        assertEquals("step 2: max:", 80, t.getMax());
        assertEquals("step 2: min:", 50, t.getMin());
        assertEquals("step 2: mean:", 65.0, t.getMean(), precision);
        assertTrue("step 2: mode:", t.getMode() == 50 || t.getMode() == 80);

        t.insert(80);
        assertEquals("step 3: max:", 80, t.getMax());
        assertEquals("step 3: min:", 50, t.getMin());
        assertEquals("step 3: mean:", 70.0, t.getMean(), precision);
        assertEquals("step 3: mode:", 80, t.getMode());

        t.insert(30);
        assertEquals("step 4: max:", 80, t.getMax());
        assertEquals("step 4: min:", 30, t.getMin());
        assertEquals("step 4: mean:", 60.0, t.getMean(), precision);
        assertEquals("step 4: mode:", 80, t.getMode());
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(MeanMaxMode.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}