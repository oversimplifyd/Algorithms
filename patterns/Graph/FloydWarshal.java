import java.util.*;

class Solution {

    public static void main(String[] args) {
        //
    }
}

class FloydWarshall {

    Graph graph; 

    public FloydWarshall(Graph graph) {
        this.graph = graph;
    }

    public void shortestPath(int root) {

        // This runs in O(n^3) times. 
        // Unlike other algorithms which  uses either an adjacency list or edge list, 
        // this use an adjacency matrix with matrix]source\]dest\ representing weights. 
        // It can also be used to detec negative weights just like BellmanFord, by checking 
        // if any od the matrix diagonal contains a negative weight which represents a negative cycle 
        // a cycle in this representatio is when you have zero in the diagronal. 

        // Algorithm is simply, for every vertex

        
        int[][] distances = new int[numberOfVertices][numberOfVertices];

        int numberOfVertices = this.graph.adjMatrix.length;

        for (int i = 0; i < numberOfVertices; i++) {
            for (int j = 0; j < numberOfVertices; j++) {
                distances[i][j] = this.graph.adjMatrix[i][j];
            }
        }

        for (int vertex = 0; vertex < numberOfVertices; vertex++) {
            for (int source = 0; source < numberOfVertices; source++) {
                for (int dest = 0; dest < numberOfVertices; dest++) {

                    // A[i][j] = A[i][k] + A[k][j];
                    int newDistance = distances[source][vertex] + distances[vertex][dest];
                    distances[source][dest] = Math.min(distances[source][dest], newDistance);
                    // save the path.. for backtracking. 
                }

                if (distances[source][source] < 0) {
                    // negative cycle 
                    throw new IllegalArgumentException();
                }
            }
        }
    }
}

class Graph {

    int numberOfVertices;
    int[][] adjMatrix = new int[numberOfVertices][numberOfVertices];

    public Graph(int[][] matrix, int numberOfVertices) {
        this.adjMatrix = matrix;
        this.numberOfVertices = numberOfVertices;
    }
}