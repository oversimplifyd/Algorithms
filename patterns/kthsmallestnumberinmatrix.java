package algorithms; 

/**
Given an N * NN∗N matrix where each row and column is sorted in ascending order, find the Kth smallest element in the matrix.
Input: Matrix=[
    [2, 6, 8], 
    [3, 7, 10],
    [5, 8, 11]
  ], 
  K=5
Output: 7
Explanation: The 5th smallest number in the matrix is 7.

Runtime -> O(Math.min(N, K) + O(NLogK)) + K logN
Space -> O(K) 
 */
import java.util.*;

class Node {
  int rowIndex; 
  int columnIndex;

  public Node(int rowIndex, int columnIndex) {
    this.rowIndex = rowIndex;
    this.columnIndex = columnIndex;
  }
}


class KthSmallestInSortedMatrix {

  public static int findKthSmallest(int[][] matrix, int k) {
    PriorityQueue<Node> minHeap = new PriorityQueue<>((a,b) -> matrix[a.rowIndex][a.columnIndex] - matrix[b.rowIndex][b.columnIndex]);
    
    for (int i = 0; i < matrix.length && i < k; i++) {  //We don't have to push more than k items 
      if (matrix[i].length > 0) {
        minHeap.offer(new Node(i, 0));
      }
    }
    
    int numberCount = 0;

    while(!minHeap.isEmpty()) {   // KlogN
      Node node = minHeap.poll();
      numberCount++; 

      if (numberCount == k) 
          return matrix[node.rowIndex][node.columnIndex];

      if (matrix[node.rowIndex].length > node.columnIndex+1) {
        minHeap.offer(new Node(node.rowIndex, node.columnIndex+1));
      }
    }

    return -1; 
  }

  public static void main(String[] args) {
    int matrix[][] = { { 2, 6, 8 }, { 3, 7, 10 }, { 5, 8, 11 } };
    int result = KthSmallestInSortedMatrix.findKthSmallest(matrix, 5);
    System.out.print("Kth smallest number is: " + result);
  }
}
