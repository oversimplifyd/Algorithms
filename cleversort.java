package algorithms;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class CleverSort {
    
    //Final solution 
    // O(n) time and 
    //O(n) space if not in place BUT O(1) space if in place. 
    public static int[] sortScores(int[] unorderedScores, int highestPossibleScore) {
        
        int[] scoreCounts = new int[highestPossibleScore + 1];
        
        for (int score : unorderedScores) {
            scoreCounts[score] = scoreCounts[score] + 1;
        }
        
        int sortedScoreIndex = 0;
        
        for (int i = highestPossibleScore; i >= 0; i--) {
            
            for (int j = 0; j < scoreCounts[i]; j++) {
                
                unorderedScores[sortedScoreIndex] = i;
                 
                sortedScoreIndex++;
            }
        }
        
        return unorderedScores;
    }
    
      //Initial Approach. 
    // O(nLogN)  caused by insertion into a TreeMap. 
    /*public static int[] sortScores(int[] unorderedScores, int highestPossibleScore) {
        
        Map<Integer, Integer> scores = addToMap(unorderedScores, highestPossibleScore);
        
        int[] sortedScores = new int[unorderedScores.length];
        int sortedScoreIndex = 0;
        
        for (Map.Entry<Integer, Integer> entry : scores.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                
                if (sortedScoreIndex != 0) {
                    int tempScore = sortedScores[sortedScoreIndex];
                    
                    if (tempScore)
                } else {
                    sortedScores[sortedScoreIndex] = entry.getKey();
                }
                
                sortedScoreIndex--;
            }
        }
        
        return sortedScores;
    }
    
    private static Map<Integer, Integer> addToMap(int[] scores, int highestPossibleScore) {
        Map<Integer, Integer> scoreMap = new HashMap<Integer, Integer>();
        // sort the scores in O(n) time
        for (int score : scores) {
            
            if (scoreMap.containsKey(score)) {
                scoreMap.put(score, scoreMap.get(score) + 1);
            } else {
                 scoreMap.put(score, 1);
            }
        }
        
        return scoreMap;
    }*/
    

    // tests

    @Test
    public void noScoresTest() {
        final int[] scores = {};
        final int[] expected = {};
        final int[] actual = sortScores(scores, 100);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void oneScoreTest() {
        final int[] scores = {55};
        final int[] expected = {55};
        final int[] actual = sortScores(scores, 100);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void twoScoresTest() {
        final int[] scores = {30, 60};
        final int[] expected = {60, 30};
        final int[] actual = sortScores(scores, 100);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void manyScoresTest() {
        final int[] scores = {37, 89, 41, 65, 91, 53};
        final int[] expected = {91, 89, 65, 53, 41, 37};
        final int[] actual = sortScores(scores, 100);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void repeatedScoresTest() {
        final int[] scores = {20, 10, 30, 30, 10, 20};
        final int[] expected = {30, 30, 20, 20, 10, 10};
        final int[] actual = sortScores(scores, 100);
        assertArrayEquals(expected, actual);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(CleverSort.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}