import java.util.*;


class Solution {
    public static void main(String[] args) {

        List<Edge> edgeList = new ArrayList<>();

        edgeList.add(new Edge(2,3));
        edgeList.add(new Edge(2,1));
        edgeList.add(new Edge(3,7));
        edgeList.add(new Edge(3,6));
        edgeList.add(new Edge(6,8));
        edgeList.add(new Edge(1,4));
        edgeList.add(new Edge(1,9));
        edgeList.add(new Edge(4,5));

        Graph graph = new Graph(edgeList, edgeList.size() + 1);

        DFS solution = new DFS(graph);

        solution.stackTraverse();
    }
}

class DFS {

    /**
     Given an undirected tree as an edge list 
     traverse all nodes using DFS. 
                        2
                3             1
             7     6        4  9
                  8        5
    
    [2,3], [2,1], 
    [3,7], [3,6],
    [6,8], [1,4]
    [1,9], [4,5]
     */

    boolean[] discovered;
    Graph graph;

    public DFS(Graph graph) {
        this.graph = graph;
        discovered = new boolean[graph.adjacencyList.size()];
    }

    public void traverse() {
        
        for (int i = 0; i < this.graph.adjacencyList.size(); i++ ) {
            this.traverse(this.graph, graph.adjacencyList.get(i), this.discovered);
        }
    }

    public void traverse(Graph graph, List<Integer> neighbours, boolean[] discovered) {

        for (int neighbour: neighbours) {
            if (!discovered[neighbour]) {
                discovered[neighbour]= true;

                System.out.println(neighbour);
                
                this.traverse(graph, graph.adjacencyList.get(neighbour), discovered);
            }
        }
    }
}

class Graph {
        
    List<List<Integer>> adjacencyList = null;

    public Graph(List<Edge> edgeList, int numberOfVertices) {
        this.adjacencyList = new ArrayList<>();

        for (int i = 0; i <= numberOfVertices; i++) {
            this.adjacencyList.add(new ArrayList<>());            
        }

        for (Edge e: edgeList) {
            this.adjacencyList.get(e.source).add(e.dest);
            this.adjacencyList.get(e.dest).add(e.source);
        }
     }
 }

class Edge {

    int source; 
    int dest; 

    public Edge(int source, int dest) {
        this.source = source;
        this.dest = dest;
    }
 }