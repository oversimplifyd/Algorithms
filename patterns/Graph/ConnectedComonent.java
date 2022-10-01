import java.util.*;

class Solution{

    public static void main(String[] args) {
        List<Edge> edges = Arrays.asList(
            new Edge(0, 4), new Edge(1, 0), new Edge(1, 2), new Edge(2, 1),
                new Edge(2, 4), new Edge(3, 1), new Edge(3, 2), new Edge(4, 3)
        );

        ConnectedComponent c = new ConnectedComponent(new Graph(edges, 5));
        System.out.println(c.isStronglyConnectedV2());
    }
}

class ConnectedComponent{

    Graph graph; 

    public ConnectedComponent(Graph graph) {
        this.graph = graph;
    }

    /**
     * A strongly connected component is a directed graph in which, 
     * a vertex can be reached from every other vertex and vice versa.
     * 
     * This can be found using DFS. 
     */

     public boolean isStronglyConnected() {

        for (int i = 0; i < this.graph.numberOfVertices; i++) {
            boolean[] visited = new boolean[this.graph.numberOfVertices];
            this.visitVertices(i, visited, this.graph.adjList);

            for (boolean v: visited) {
                if (!v) {
                    return false;
                }
            }
        }

        return true;
     }

     public boolean isStronglyConnectedV2() {

        int vertex = 0;
        if (!this.isConnected(vertex, this.graph.adjList)) {
            return false;
        }

        List<List<Integer>> adjList = this.invertEdges();

        if (! this.isConnected(vertex, adjList)) {
            return false;
        }

        return true;
     }

     private boolean isConnected(int vertex, List<List<Integer>> adjList) {
        boolean[] visited = new boolean[this.graph.numberOfVertices];
        this.visitVertices(vertex, visited, adjList);

       for (boolean v: visited) {
            if (!v) {
                return false;
            }
        }

        return true;
     }

     private List<List<Integer>> invertEdges(){

        List<List<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < this.graph.numberOfVertices; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < this.graph.numberOfVertices; i++) {
            List<Integer> edges = this.graph.adjList.get(i);
            for (int edge: edges) {
                adjList.get(edge).add(i);
            }
        }

        return adjList;
     }

     private void visitVertices(int vertex, boolean[] visited, List<List<Integer>> adjList) {
        visited[vertex] = true;

        for (int i = 0; i < adjList.get(vertex).size(); i++) {
            int nextVertex = adjList.get(vertex).get(i);
            if (!visited[nextVertex]) {
                this.visitVertices(nextVertex, visited, adjList);
            }
        }
     }
}

class Graph {

    List<List<Integer>> adjList  = new ArrayList<>();
    int numberOfVertices;

    public Graph(List<Edge> edgeList, int numberOfVertices) {

        for (int i = 0; i < numberOfVertices; i++) {
            this.adjList.add(new ArrayList<>());
        }

        for (Edge e: edgeList) {
            // di graph
            this.adjList.get(e.source).add(e.dest);
        }

        this.numberOfVertices = numberOfVertices;
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

