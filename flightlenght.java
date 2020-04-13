/**
 * Using HashSet to beat time complexity with problems involing complement. 
 * */

package algorithms; 

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

public class FlightLength {

    public static boolean canTwoMoviesFillFlight(int[] movieLengths, int flightLength) {

        // determine if two movies add up to the flight length
        
        
        //Best Approach -> HashSet 
        // Find the complement of each number in a HashSet containing the numbers. 
        
        Set<Integer> moviesSeenSet = new HashSet<>();
        
        for (int firstMovieLength : movieLengths) {
            
            int firstMovieLengthComplement = flightLength - firstMovieLength;
            
            if (moviesSeenSet.contains(firstMovieLengthComplement)) {
                //Lookup O(1) 
                return true; 
            }
            
            moviesSeenSet.add(firstMovieLength);
        }
        
        
        //Second Approach 
        // O(2n + nLogn) = nLogn
       /* Arrays.sort(movieLengths);
        for (int i =0; i < movieLengths.length - 1; i++) {
          
            //Try to sum adjacent values, 
            //when we come accross a bigger sum
            //Take the index of the adjacent and attempt summation with previous indexes. 
            
            if (movieLengths[i] + movieLengths[i + 1] == flightLength) {
                return true; 
            } else if (movieLengths[i] + movieLengths[i + 1] > flightLength) {
                
                int k = i + 1;
                if (k < 2) return false;
                
                for (int j = 0; j < k; j++) {
                    if (movieLengths[j] + movieLengths[k] == flightLength) {
                        return true;
                    }
                }
                
                return false;
            }
        }*/
        
        //Last Approach 
        //Initial Solution 
        // O(n^2)
       /* for (int i = 0; i < movieLengths.length; i++) {
            for (int j = i + 1; j < movieLengths.length; j++) {
                if (movieLengths[i] + movieLengths[j] == flightLength) {
                    return true;
                }
            }
        }
*/
        return false;
    }


    // tests

    @Test
    public void shortFlightTest() {
        final boolean result = canTwoMoviesFillFlight(new int[] {2, 4}, 1);
        assertFalse(result);
    }

    @Test
    public void longFlightTest() {
        final boolean result = canTwoMoviesFillFlight(new int[] {2, 4}, 6);
        assertTrue(result);
    }

    @Test
    public void onlyOneMovieHalfFlightLenghtTest() {
        final boolean result = canTwoMoviesFillFlight(new int[] {3, 8}, 6);
        assertFalse(result);
    }

    @Test
    public void twoMoviesHalfFlightLengthTest() {
        final boolean result = canTwoMoviesFillFlight(new int[] {3, 8, 3}, 6);
        assertTrue(result);
    }

    @Test
    public void lotsOfPossiblePairsTest() {
        final boolean result = canTwoMoviesFillFlight(new int[] {1, 2, 3, 4, 5, 6}, 7);
        assertTrue(result);
    }

    @Test
    public void notUsingFirstMovieTest() {
        final boolean result = canTwoMoviesFillFlight(new int[] {4, 3, 2}, 5);
        assertTrue(result);
    }

    @Test
    public void oneMovieTest() {
        final boolean result = canTwoMoviesFillFlight(new int[] {6}, 6);
        assertFalse(result);
    }

    @Test
    public void noMoviesTest() {
        final boolean result = canTwoMoviesFillFlight(new int[] {}, 6);
        assertFalse(result);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(FlightLength.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}