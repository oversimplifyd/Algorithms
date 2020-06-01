package algorithms; 

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class Solution {

    public static class CakeType {

        final int weight;
        final int value;

        public CakeType(int weight, int value) {
            this.weight = weight;
            this.value  = value;
        }
    }
    
    public static class InfinityException extends RuntimeException {
        
        public InfinityException() {
            super("Infinity..exception");
        }
    }
    
    public static long maxDuffelBagValue(CakeType[] cakeTypes, int weightCapacity) {

        //Addednum: 
        /** For a classic 0/1 Knpsack problem 
        * Create overlapping subproblems for subcapacities (0 - Capacity) 
        * Using the capacity index a Row instead of computing using two dimensiatonal array. This can be solved in O(N) space 
        * The Math.max(previousValueForCapacity, valueOfCurrentItem + bestAnserforRemainder) => Math.max(V[i-1][j], currentValueforcurrentWeight + V[i-1][j - currentWeight])
        * DP provides an optimal solution but can be very slow thus not efficient. There are other solutions that gives good but not optimal answer and efficient such as using just Greedy algorithm 
        * In the same vein, Greedy algorithm might be efficient but not optimal. That is, it won't always give the best answer especially for problem involving DP 
        * Reference 
        * https://medium.com/@fabianterh/how-to-solve-the-knapsack-problem-with-dynamic-programming-eb88c706d3cf 
         */

        // Time => O(nm) ->N capacity, m weights 
        // Space => 09N
        // calculate the maximum value that we can carry
        
        // There are overlapping subprolems because
        //The solution I get for smaller values of target can be combined to get the solution for target 
        // Also, the soution required for target is the same as the solution required for its subproblems 
        
        // Classic 0-1 Knapsack problem 
        // For the curent weight, choose the maximum between the (previous maximum for the weight without the currentItem AND currentWeightValue + the value of its remainderWeight (subproblem)  
        
        int[] maxValueCapacity = new int[weightCapacity + 1];
        
        for (int currentCapacity = 0; currentCapacity <= weightCapacity; currentCapacity++) {
            
            int currentValueForCapacityConsdieringAllCakes = 0; 
            
            for (CakeType cakeType : cakeTypes) {
                
                //Edge case: 
                if (cakeType.weight == 0 && cakeType.value != 0) {
                    throw new InfinityException();
                }
                
                if (cakeType.weight <= currentCapacity) {
                    // If this cake can fit into this capacity 
                    //Get the value for cake after fitting and the best value for the leftOver weight 
                    int maxValueForCurrentCake = cakeType.value + maxValueCapacity[currentCapacity - cakeType.weight]; 
                    
                    // curentValue is the est anser we have so far for this currentCapacity. 
                    //We need the best anser after considering all weights 
                    currentValueForCapacityConsdieringAllCakes = Math.max(maxValueForCurrentCake, currentValueForCapacityConsdieringAllCakes);
                }
            }
            
            maxValueCapacity[currentCapacity] = currentValueForCapacityConsdieringAllCakes;
        }
        
        return  maxValueCapacity[weightCapacity]; 
    }
    
    
    

    // tests

    @Test
    public void oneCakeTest() {
        final CakeType[] cakeTypes = {new CakeType(2, 1)};
        final int weightCapacity = 9;
        final long expected = 4;
        final long actual = maxDuffelBagValue(cakeTypes, weightCapacity);
        assertEquals(expected, actual);
    }

    @Test
    public void twoCakesTest() {
        final CakeType[] cakeTypes = {new CakeType(4, 4), new CakeType(5, 5)};
        final int weightCapacity = 9;
        final long expected = 9;
        final long actual = maxDuffelBagValue(cakeTypes, weightCapacity);
        assertEquals(expected, actual);
    }

    @Test
    public void onlyTakeLessValuableCakeTest() {
        final CakeType[] cakeTypes = {new CakeType(4, 4), new CakeType(5, 5)};
        final int weightCapacity = 12;
        final long expected = 12;
        final long actual = maxDuffelBagValue(cakeTypes, weightCapacity);
        assertEquals(expected, actual);
    }

    @Test
    public void lotsOfCakesTest() {
        final CakeType[] cakeTypes = {
            new CakeType(2, 3), new CakeType(3, 6), new CakeType(5, 1),
            new CakeType(6, 1), new CakeType(7, 1), new CakeType(8, 1)
        };
        final int weightCapacity = 7;
        final long expected = 12;
        final long actual = maxDuffelBagValue(cakeTypes, weightCapacity);
        assertEquals(expected, actual);
    }

    @Test
    public void valueToWeightRatioIsNotOptimalTest() {
        final CakeType[] cakeTypes = {new CakeType(51, 52), new CakeType(50, 50)};
        final int weightCapacity = 100;
        final long expected = 100;
        final long actual = maxDuffelBagValue(cakeTypes, weightCapacity);
        assertEquals(expected, actual);
    }

    @Test
    public void zeroCapacityTest() {
        final CakeType[] cakeTypes = {new CakeType(1, 2)};
        final int weightCapacity = 0;
        final long expected = 0;
        final long actual = maxDuffelBagValue(cakeTypes, weightCapacity);
        assertEquals(expected, actual);
    }

    @Test
    public void cakeWithZeroValueAndWeightTest() {
        final CakeType[] cakeTypes = {new CakeType(0, 0), new CakeType(2, 1)};
        final int weightCapacity = 7;
        final long expected = 3;
        final long actual = maxDuffelBagValue(cakeTypes, weightCapacity);
        assertEquals(expected, actual);
    }

    @Test(expected = Exception.class)
    public void cakeWithNonZeroValueAndZeroWeightTest() {
        final CakeType[] cakeTypes = {new CakeType(0, 5)};
        final int weightCapacity = 5;
        maxDuffelBagValue(cakeTypes, weightCapacity);
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