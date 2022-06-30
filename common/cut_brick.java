import java.io.*;
import java.util.*;

/*

You are working for a construction firm, and need to check if a wall made of different size bricks can be split without cutting through any of the bricks.

For the example, this wall can be split on position 7 and at position 9:

      0   1   2   3   4   5   6   7   8   9   10  11  12  13
                                  |       |
                                  V       V 
      +---+---+---+---+---+---+---+---+---+---+---+---+---+
      |             7             | 1 | 1 |       4       |
      +---+---+---+---+---+---+---+---+---+---+---+---+---+
      |         5         | 1 | 1 |   2   |   2   |   2   |
      +---+---+---+---+---+---+---+---+---+---+---+---+---+
      |     3     |       4       | 1 | 1 |     3     | 1 |
      +---+---+---+---+---+---+---+---+---+---+---+---+---+
      | 1 | 1 | 1 | 1 |     3     |   2   | 1 |     3     |
      +---+---+---+---+---+---+---+---+---+---+---+---+---+
  [7,1,1,4],
  
  7 + 1 + 1 + 4 = 13
    
  7 => 0,1,2,3,4,5,6
  1 => 7
  1 => 8
  4 => 9, 10, 11, 12
  
  1 => 5 
  1 => 6
  2 => 7 
  2 => 9
  2 =>.11 
  
  3 => 0, 1, 2
  4 => 3, 4, 5, 6
  1 => 7
  1 => 8
  3 => 9, 10, 11
  1 => 12
  
  [5,1,1,2,2,2],
  
  5 + 1 + 1 + 2 + 2 + 2 = 13 
  
  index -> count 
  1 -> 
  2 -> 
  3 -> 
  7 -> 4



Here is the sample input for the wall depicted above:

brick_wall_1 = [
  [7,1,1,4],
  [5,1,1,2,2,2],
  [3,4,1,1,3,1],
  [1,1,1,1,3,2,1,3]
]

Write a function that takes this data as input and returns all the positions where a wall can be split.

get_split_positions(brick_wall_1) => [ 7, 9 ]

And here are other examples:

brick_wall_2 = [
  [2,2,2,1],
  [4,3],
  [3,4]
]

brick_wall_3 = [
  [9,1],
  [5,4,1],
  [3,3,2,1,1],
  [6,3,1],
]

brick_wall_4 = [
  [1,1,1,1,1,1,1,1],
  [1,1,1,1,1,1,1,1],
  [1,1,1,1,1,1,1,1]
]

brick_wall_5 = [
  [1,5,1]
]

brick_wall_6 = [
  [3],
  [3]
]

get_split_positions(brick_wall_1) => [7,9]
get_split_positions(brick_wall_2) => []
get_split_positions(brick_wall_3) => [9]
get_split_positions(brick_wall_4) => [1,2,3,4,5,6,7]
get_split_positions(brick_wall_5) => [1,6]
get_split_positions(brick_wall_6) => []

Complexity analysis variables:

n: number of bricks in the wall
w: wall width
h: wall height (in layers)


*/

/**
 int[][] brick_wall_2 = {
      {2, 2, 2, 1},
      {4, 3},
      {3, 4}
    };

    int[][] brick_wall_3 = {
      {9, 1},
      {5, 4, 1},
      {3, 3, 2, 1, 1},
      {6, 3, 1}
    };

    int[][] brick_wall_4 = {
      {1, 1, 1, 1, 1, 1, 1, 1},
      {1, 1, 1, 1, 1, 1, 1, 1},
      {1, 1, 1, 1, 1, 1, 1, 1}
    };

    int[][] brick_wall_5 = {
      {1, 5, 1}
    };

    int[][] brick_wall_6 = {
      {3},
      {3}
    };
**/
public class Solution {
  public static void main(String[] argv) {
    int[][] brick_wall_1 = {
      {7, 1, 1, 4},
      {5, 1, 1, 2, 2, 2},
      {3, 4, 1, 1, 3, 1},
      {1, 1, 1, 1, 3, 2, 1, 3}
    };
    
    HashMap<Integer, Integer> map = new HashMap<>();
            
    for (int row = 0; row < brick_wall_1.length; row++) {
      
        int prevWallEndIndex = 0;
    
      for (int col = 0; col < brick_wall_1[row].length; col++) {
        
        int wallSize = brick_wall_1[row][col];
        int[] result = new int[2];
        
        result = getWallPosition(wallSize, prevWallEndIndex);
        
        map.put(result[0], map.getOrDefault(result[0], 0) + 1);
        prevWallEndIndex = result[1];
      }
    }
        
    for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
      if (entry.getValue() == brick_wall_1.length && entry.getKey() != 0) {
        System.out.println(entry.getKey());
      }
    }
  }
  
  public static int[] getWallPosition(int currentWallSize, int prevWallEndIndex) {
    
    int start = prevWallEndIndex; 
    int end = start + currentWallSize;
    
    int[] result = {start, end};
        
    return result;
  }
}

