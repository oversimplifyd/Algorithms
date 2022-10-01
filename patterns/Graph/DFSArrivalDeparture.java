import java.util.*;

class Solution {
    public static void main(String[] args) {

        List<Edge> edgeList = Arrays.asList( new Edge(0, 1), new Edge(0, 2), new Edge(2, 3), new Edge(2, 4),
        new Edge(3, 1), new Edge(3, 5), new Edge(4, 5), new Edge(6, 7));

        Graph graph = new Graph(edgeList, 8);

        DFSArrivalDeparture solution = new DFSArrivalDeparture(graph);

        solution.traverse();
    }
}

class DFSArrivalDeparture {

    boolean[] discovered;
    Graph graph;

    public DFSArrivalDeparture(Graph graph) {
        this.graph = graph;
        discovered = new boolean[graph.adjacencyList.size()];
    }

    public void traverse() {
        int[] arrival = new int[this.graph.numberOfVertices];
        int[] departure = new int[this.graph.numberOfVertices];

        int time = -1;

        for (int i = 0; i < this.graph.numberOfVertices; i++)
        {
            if (!discovered[i]) {
                time = this.traverse(i, discovered, time, arrival, departure);
            }
        }
        //this.traverse(0, discovered, time, arrival, departure);

        // print..
        for (int i = 0; i < arrival.length; i++) {
            System.out.println(i);
            System.out.println(arrival[i]);
            System.out.println(departure[i]);

            System.out.println("B R E A K..");
        }
    }

    public int traverse(int vertex, boolean[] discovered, int time, int[] arrival, int[] departure) {

        discovered[vertex] = true;
        arrival[vertex] = ++time;

        for (int i = 0; i < this.graph.adjacencyList.get(vertex).size(); i++) {
            int current = this.graph.adjacencyList.get(vertex).get(i);

            if (!discovered[current]) {
                time = this.traverse(current, discovered, time, arrival, departure);
            }
        }

        departure[vertex] = ++time;

        return time;
    }
}

class Graph {
        
    List<List<Integer>> adjacencyList = null;
    int numberOfVertices;

    public Graph(List<Edge> edgeList, int numberOfVertices) {
        this.adjacencyList = new ArrayList<>();

        this.numberOfVertices = numberOfVertices;

        for (int i = 0; i <= numberOfVertices; i++) {
            this.adjacencyList.add(new ArrayList<>());            
        }

        for (Edge e: edgeList) {
            this.adjacencyList.get(e.source).add(e.dest);
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