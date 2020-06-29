package algorithms; 
/**
A Toeplitz matrix is a matrix where every left-to-right-descending diagonal has the same element. Given a non-empty matrix arr, write a function that returns true if and only if it is a Toeplitz matrix. The matrix can be any dimensions, not necessarily square.

For example,

[[1,2,3,4],
 [5,1,2,3],
 [6,5,1,2]]
\ */

import java.io.*;
import java.util.*;

class Solution {
  
  // Using a Map <Beautiful solution> 
  // Time Complexity -> O(row * col)
  // Space Complexity -> O(row + column) 
  static boolean isToeplitz(int[][] arr) {
    
    // The general idea behind this is to store the hash 
    // of r - c unto a map. Such that: rpc -> arr[r][c]
    // Fact: For a Toeplitz matrix, the value at r-c at any point in time must be the same
    // That is, r-c holds the result of every correspondig diagonal 
    
    /**
      [8,8,8,8,8],   0-3  -3
      [8,8,8,8,9],   1-4  -3
      [8,8,8,8,8],
      [8,8,8,8,8],
      [8,8,8,8,8]
    **/
    Map<Integer, Integer> diagonalsSeen = new HashMap<>();
    
    for (int row = 0; row < arr.length; row++) {
      for (int col = 0; col < arr[0].length; col++) {
        if (!diagonalsSeen.containsKey(row-col)) {
          diagonalsSeen.put(row-col, arr[row][col]);
        }
        
        if (diagonalsSeen.get(row-col) != arr[row][col]) return false; 
      }
    }
    
    return true; 
  }
  
  //++++++++++++++++++++++++++ S O L U T I O N 2 +++++++++++++++++++++++++++++++++++
  // Time Complexity -> O(row * column); 
  // Space O(1) 
  static boolean isToeplitzAlgebraicSolution(int[][] arr) {
    
    int rows = arr.length; 
    int columns = arr[0].length; 
    
    //In the first row, check each of its column and its diagonals 
    // for subsequent rows, we only need to check the first column of this row since the other work has been done in the first row been the longest dianonals 
    
    for (int firstRowColumn = 0; firstRowColumn < columns - 1; firstRowColumn++) {
      
      int startDiagnoalValue = arr[0][firstRowColumn];
      // we can derive a K for checking these diagnoals since 
      //FirstRowColumns         Diagnoals 
      //0,0             0,0 1,1 2,2... 
      //0,1             0,1 1,2 2,3 ...
      //0, 2            
      // deriving K -> denoting the number of iterations we must do for subsequent rows, cplumns
      // k = Math.min(Rows, Columns-curentColumn - 1)
      
      for (int k = 0; k <= Math.min(rows, columns - firstRowColumn) - 1; k++) {
        if (arr[k][firstRowColumn+k] != startDiagnoalValue) {
          return false; 
        }
      }
    }
    
    // We need to do the same for the first columns of every other row :. 
    for (int otherRows = 1; otherRows < rows - 1; otherRows++) {
      int startValueOfDiagonal = arr[otherRows][0];
      
      for (int k = 0; k <= Math.min(columns, rows - otherRows) - 1; k++) {
        if (arr[otherRows+k][k] != startValueOfDiagonal) {
          return false; 
        }
      }
    }
    
    return true; 
  }
  
  //++++++++++++++++++++++++++ S O L U T I O N 3 +++++++++++++++++++++++++++++++++++
  // Naitve Solution. 
  // inefficient 
  // Time complexity => O(row * Col * lengthOfMaximumDiagonal) N^3 for a square matrix 
	/**static boolean isToeplitz(int[][] arr) {
    for (int row = 0; row < arr.length; row++) {  
      for (int col = 0; col < arr[0].length; col++) {    // O(N * (m* length of longest diagonal)) 
        int value = arr[row][col];
        
        int nextDiagonalsStart = row + 1; 
        
        int nextRow = row; 
        int nextCol = col; 
        
        while (nextDiagonalsStart < arr.length) {
          if (nextCol == arr[0].length - 1) break;
          nextRow++;
          nextCol++;
          if (value != arr[nextRow][nextCol]) return false; 
          nextDiagonalsStart++; 
        }
      }
    }
    return true; 
  } **/
  
  public static void main(String[] args) {
    
  }
}


// C++ Implementation 

// #import <iostream>
// #import <vector>

// using namespace std;

// bool isToeplitz(const vector<vector<int>>& arr)
// {
//   if (arr.empty()) {
//     return true;
//   }
  
  
//   for (size_t row = 0; row < arr.size(); ++row) {
//     int value = arr[row][0];
//     for (size_t delta = 0; row + delta < arr.size() && delta < arr[row].size(); ++delta) {
//       if (arr[row + delta][delta] != value) {
//         return false;
//       }
//     }
//   }
  
//   for (size_t col = 0; col < arr[0].size(); ++row) {
//     int value = arr[0][col];
//     for (size_t delta = 0; col + delta < arr[0].size() && delta < arr.size(); ++delta) {
//       if (arr[delta][col + delta] != value) {
//         return false;
//       }
//     }
//   }
  
//   return true;
// }

// int main() 
// {
// 	return 0;
// }


// // col [i, j], [i + 1, j + 1], [i + 2, j + 2]