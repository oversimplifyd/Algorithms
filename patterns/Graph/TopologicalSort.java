import java.util.*;

class Solution {

    public static void main(String[] args) {
        List<Edge> edges = Arrays.asList(
            new Edge(0, 6), new Edge(1, 2), new Edge(1, 4),
            new Edge(1, 6), new Edge(3, 0), new Edge(3, 4),
            new Edge(5, 1), new Edge(7, 0), new Edge(7, 1)
        );

        Graph graph = new Graph(edges, 8);
        TopologicalSort ts = new TopologicalSort(graph);

        ts.sort();
    }
}

class TopologicalSort{

    Graph graph;

    public TopologicalSort(Graph graph) {
        this.graph = graph;
    }

    public void sort(){

        //To avoid having to sort again, 
        // this is troing the actual time against the vertex as opposed to the 
        // vertex against the actual time. 
        // the only disaddvantage of this is it results in a sparse array. 
        int[] departureTime = new int[2*this.graph.numberOfVerticies];

        Arrays.fill(departureTime, -1);

        boolean[] visited = new boolean[this.graph.numberOfVerticies];
        int time = -1;

        for (int i = 0; i < this.graph.numberOfVerticies; i++) {
            if (!visited[i]) {
                time = this.traverse(i, visited, departureTime, time);
            }
        }

        // we can then traverse departure time from the back 
        for (int i = 2 * this.graph.numberOfVerticies - 1; i > 0; i--) {
            // we know we will have a sparse array as a trade of for time
            // so skip no value slots. 
            if (departureTime[i] != -1) {
                System.out.println(departureTime[i]);
            }
        }
    }

    private int traverse(int vertex, boolean[] visited, int[] departureTime, int time) {

        visited[vertex] = true;
        time++;

        List<Integer> outgoingEdges = this.graph.adjList.get(vertex);

        for (int currentVertex: outgoingEdges) {
            if (!visited[currentVertex]) {
                time = this.traverse(currentVertex, visited, departureTime, time);
            }
        }

        //backtracking.
        time++;
        departureTime[time] = vertex;

        return time;
    }
}

class Graph{

    List<List<Integer>> adjList; 
    int numberOfVerticies; 

    public Graph(List<Edge> edges, int numberOfVerticies) {
        this.numberOfVerticies = numberOfVerticies;

        this.adjList = new ArrayList<>();

        for (int i = 0; i < numberOfVerticies; i++) {
            this.adjList.add(i, new ArrayList<>())    ;
        }

        for (Edge e: edges) {
            // di-graph.
            this.adjList.get(e.source).add(e.dest);
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
