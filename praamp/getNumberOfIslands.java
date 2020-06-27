import java.io.*;
import java.util.*;


  /**
  Given a 2D array binaryMatrix of 0s and 1s, implement a function getNumberOfIslands that returns the number of islands of 1s in binaryMatrix.

An island is defined as a group of adjacent values that are all 1s. A cell in binaryMatrix is considered adjacent to another cell if they are next to each either on the same row or column. Note that two values of 1 are not part of the same island if they’re sharing only a mutual “corner” (i.e. they are diagonally neighbors).

Explain and code the most efficient solution possible and analyze its time and space complexities.

Example:

input:  binaryMatrix = [ [0,    1,    0,    1,    0],
                         [0,    0,    1,    1,    1],
                         [1,    0,    0,    1,    0],
                         [0,    1,    1,    0,    0],
                         [1,    0,    1,    0,    1] ]
                         
  **/

// Time -> o(N*M) 
// Space O(N*M)

class Solution {
  
  static int getNumberOfIslands(int[][] binaryMatrix) {
    
    int counter = 0;
    
    for (int row = 0; row < binaryMatrix.length; row++) {
      for (int col = 0; col < binaryMatrix[row].length; col++) {
        if (binaryMatrix[row][col] == 1) {
           markAsSeen(binaryMatrix, binaryMatrix.length, binaryMatrix[row].length, row, col);
           counter++;
        }
      }
    }
    
    return counter; 
  }
  
  // Using BFS, where the connected ISLANDs are thought of as undirected acyclic graph.v
  static void markAsSeen(int[][] binaryMatrix, int rows, int cols, int row, int col) {
    
    Deque<Cell> queue = new ArrayDeque<>();
    
    queue.offer(new Cell(row, col));
    
    while (!queue.isEmpty()) {
      Cell currentCell = queue.poll();
      
      int rowI = currentCell.rowIndex;
      int colI = currentCell.colIndex; 
      
      if (binaryMatrix[rowI][colI] == 1) {
        binaryMatrix[rowI][colI] = 0;
      }
      
      if (canPushToQueue(binaryMatrix, rows, cols, rowI, colI + 1)) {
        queue.offer(new Cell(rowI, colI+1));
      }
      
      if (canPushToQueue(binaryMatrix, rows, cols, rowI, colI - 1)) {
        queue.offer(new Cell(rowI, colI-1));
      }
      
      if (canPushToQueue(binaryMatrix, rows, cols, rowI + 1, colI)) {
        queue.offer(new Cell(rowI+1, colI));
      }
      
      if (canPushToQueue(binaryMatrix, rows, cols, rowI - 1, colI)) {
        queue.offer(new Cell(rowI-1, colI));
      }
     }
    }
  
    static boolean canPushToQueue(int[][] binaryMatrix, int rowLength, int colLength, int row, int col) {
      if (row < rowLength && col < colLength && row >= 0 && col >= 0) {
        if (binaryMatrix[row][col] == 1) return true; 
      }
      return false; 
    }
  
    public static void main(String[] args) {

    }
  }

 /** R E C U R S I V E 
 
 static void markAsSeen(int[][] binaryMatrix, int row, int col) {
   
   if (row > binaryMatrix.length - 1 || col > binaryMatrix[row].length - 1 || row < 0 || col < 0) {
     return; 
   }
   
   if (binaryMatrix[row][col] == 0) return; 
    
   binaryMatrix[row][col] = 0; 
   
   markAsSeen(binaryMatrix, row, col+1);
   markAsSeen(binaryMatrix, row+1, col);
   markAsSeen(binaryMatrix, row, col-1);
 } **/

  class Cell {
    int rowIndex; 
    int colIndex; 
    
    public Cell(int rowIndex, int colIndex) {
      this.rowIndex = rowIndex; 
      this.colIndex = colIndex;
    }
  }
