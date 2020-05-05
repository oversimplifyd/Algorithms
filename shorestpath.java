package algorithms;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.ArrayDeque;
import java.util.Queue;

import java.util.Collections;

import java.util.Set;
import java.util.HashSet;

import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ShortestPath {
    
    // Mesh Network 

    /** 
    * My Approach 
    * Runtime O(N+M) 
    * Space complexity O(NLogN)
    */
    // public static String[] getPath(Map<String, String[]> graph, String startNode, String endNode) {
        
    //     if (startNode.equals(endNode)) {
    //         return new String[]{startNode};
    //     }
        
    //     if (!graph.containsKey(endNode)) {
    //          throw new IllegalArgumentException("Invalid no endtime");
    //     }
        
    //     Queue<Map<String, ArrayList<String>>> graphQueue = new ArrayDeque<Map<String, ArrayList<String>>>();
        
    //     Map<String, ArrayList<String>> initialNode = new HashMap<String, ArrayList<String>>();
    //     initialNode.put(startNode, new ArrayList<String>());
    //     graphQueue.add(initialNode);
        
    //     Set<String> visitedNeighbours = new HashSet<String>();
        
    //     while (!graphQueue.isEmpty()) {
    //         Map<String, ArrayList<String>> begin = graphQueue.remove();
            
    //         String currentNode = (String) begin.keySet().toArray()[0];
    //         ArrayList<String> path =new ArrayList<String>(begin.get(currentNode));
            
    //         path.add(currentNode);
    //         visitedNeighbours.add(currentNode);
            
    //         for(String neighbour : graph.get(currentNode)) {
                
    //             if (neighbour.equals(endNode)) {
    //                 path.add(neighbour);
    //                 return path.toArray(new String[0]);
    //             } else {
    //                 if (!visitedNeighbours.contains(neighbour)) {
    //                     visitedNeighbours.add(neighbour);
    //                     Map<String, ArrayList<String>> nextNode = new HashMap<String, ArrayList<String>>();
    //                     nextNode.put(neighbour, path);
    //                     graphQueue.add(nextNode);
    //                 }
    //             }
    //         }
    //         // increment path, how do I tack path 
    //     }
        
    //     return null;
    // }

    /** 
    * My Approach 
    * Runtime O(N+M) 
    * Space complexity O(N) 
    * Although there were relevant speed implications. 
    * Much simplified 
    */
    public static String[] getPath(Map<String, String[]> graph, String startNode, String endNode) {
        
        if (startNode.equals(endNode)) {
            return new String[]{startNode};
        }
        
        if (!graph.containsKey(endNode)) {
             throw new IllegalArgumentException("Invalid no endtime");
        }
        
        Queue<String> graphQueue = new ArrayDeque<String>();
        Map<String, String> currentNodePreviousNode = new HashMap<>();

        currentNodePreviousNode.put(startNode, null);
        graphQueue.add(startNode);
        
        // Set<String> visitedNeighbours = new HashSet<String>(); -> this can now be gotten from the keys in the currentNodePreviousNode we want to use to reconstruct path 
        
        while (!graphQueue.isEmpty()) {
            String currentNode = graphQueue.remove();

            if (currentNode.equals(endNode)) {
                return reconstructPath(currentNodePreviousNode, endNode);
            }

            for(String neighbour : graph.get(currentNode)) {
                if (!currentNodePreviousNode.containsKey(neighbour)) {
                        currentNodePreviousNode.put(neighbour, currentNode);
                        graphQueue.add(neighbour);
                }
            }
        }
        
        return null;
    }

    private static String[] reconstructPath(Map<String, String> howWeMoved, String endNode) {

        List<String> path = new ArrayList<String>();

        String currentNode = endNode;

        while (currentNode != null) {
            path.add(currentNode);
            currentNode = howWeMoved.get(currentNode);
        }

        Collections.reverse(path);

        return path.toArray(new String[path.size()]);
    }

    // tests

    @Test
    public void twoHopPath1Test() {
        final String[] expected = {"a", "c", "e"};
        final String[] actual = getPath(getNetwork(), "a", "e");
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void twoHopPath2Test() {
        final String[] expected = {"d", "a", "c"};
        final String[] actual = getPath(getNetwork(), "d", "c");
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void oneHopPath1Test() {
        final String[] expected = {"a", "c"};
        final String[] actual = getPath(getNetwork(), "a", "c");
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void oneHopPath2Test() {
        final String[] expected = {"f", "g"};
        final String[] actual = getPath(getNetwork(), "f", "g");
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void oneHopPath3Test() {
        final String[] expected = {"g", "f"};
        final String[] actual = getPath(getNetwork(), "g", "f");
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }
    
   @Test
    public void zeroHopPath() {
        final String[] expected = {"a"};
        final String[] actual = getPath(getNetwork(), "a", "a");
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void noPathTest() {
        final String[] actual = getPath(getNetwork(), "a", "f");
        assertNull(actual);
    }

    @Test(expected = Exception.class)
    public void startNodeNotPresentTest() {
        getPath(getNetwork(), "h", "a");
    }

    @Test(expected = Exception.class)
    public void endNodeNotPresentTest() {
        getPath(getNetwork(), "a", "h");
    }

    private static Map<String, String[]> getNetwork() {
        return new HashMap<String, String[]>() { {
            put("a", new String[] {"b", "c", "d"});
            put("b", new String[] {"a", "d"});
            put("c", new String[] {"a", "e"});
            put("d", new String[] {"a", "b"});
            put("e", new String[] {"c"});
            put("f", new String[] {"g"});
            put("g", new String[] {"f"});
        }};
    }

        Result result = JUnitCore.runClasses(ShortestPath.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}