package algorithms; 

import java.util.ArrayList;
import java.util.List;

import java.util.Arrays;

public class MackeChange {
    
    public static int changePossibilities(int amount, int[] denominations) {

        // O(nm)  -> Where N = amount and M = Denominations 
        // O(n) -> Space 
        //This is a classic DP problem 
        //https://en.wikipedia.org/wiki/Change-making_problem
        
        //DP Table -> Bottom-up algorithm -> More explicit table structure 
        /**
        * ways[coin][amount]
        * foreach coin 
        *     for each subproblems of amount up to amount 
        *           if (coin < amount) 
        *               ways[coin][amount] = ways[coin - 1][amount]
        *           else 
        *               ways[coin][amount] = ways[coin - 1][amount] + ways[coin][amount - coin]
        * return ways[coin[coin.length - 1]][amount]

        * Read & Understand the question
        * Solve it by hand, look for patterns, then perhaps brute-force, try some DS 
        * Draw out sample inputs, 
        * Solve a smaller size of the problem 
        * Litten to the interviewer, slow the eff down 
        * Communicate your thought process. Take a brief time to think. 
        * If you find a solution, look for a better one. 
        * Think about edge cases 
        * Run your solution on sample imputs

        * BE CONFIDENT, STAY CONFIDENT
        */

        int[] waysToComputeSubProblemPerCoin = new int[amount + 1];
        
        //Base Case; 
        waysToComputeSubProblemPerCoin[0] = 1; 
        
        for (int coin: denominations) {
            for (int highest = coin; highest <= amount; highest++) {
                int remainder = highest - coin;
                waysToComputeSubProblemPerCoin[highest] = waysToComputeSubProblemPerCoin[highest] + waysToComputeSubProblemPerCoin[remainder];
            }
        }
        
        //The last itme is th etotal number of ways. 
        return waysToComputeSubProblemPerCoin[amount];
    }

    // tests

    // @Test
    // public void sampleInputTest() {
    //     final int actual = changePossibilities(4, new int[] {1, 2, 3});
    //     final int expected = 4;
    //     assertEquals(expected, actual);
    // }

    // @Test
    // public void oneWayToMakeZeroCentsTest() {
    //     final int actual = changePossibilities(0, new int[] {1, 2});
    //     final int expected = 1;
    //     assertEquals(expected, actual);
    // }

    // @Test
    // public void noWaysIfNoCoinsTest() {
    //     final int actual = changePossibilities(1, new int[] {});
    //     final int expected = 0;
    //     assertEquals(expected, actual);
    // }

    // @Test
    // public void bigCoinValueTest() {
    //     final int actual = changePossibilities(5, new int[] {25, 50});
    //     final int expected = 0;
    //     assertEquals(expected, actual);
    // }

    // @Test
    // public void bigTargetAmountTest() {
    //     final int actual = changePossibilities(50, new int[] {5, 10});
    //     final int expected = 6;
    //     assertEquals(expected, actual);
    // }

    // @Test
    // public void changeForOneDollarTest() {
    //     final int actual = changePossibilities(100, new int[] {1, 5, 10, 25, 50});
    //     final int expected = 292;
    //     assertEquals(expected, actual);
    // }

    public static void main(String[] args) {
        //Result result = JUnitCore.runClasses(MackeChange.class);
        // for (Failure failure : result.getFailures()) {
        //     System.out.println(failure.toString());
        // }
        // if (result.wasSuccessful()) {
        //     System.out.println("All tests passed.");
        // }
        final int actual = changePossibilities(7, new int[] {1, 2, 4});
       
       System.out.println(actual); 
    }
}