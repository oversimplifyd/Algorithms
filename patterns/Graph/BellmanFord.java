import java.util.*;

class Solution {

    public static void main(String[] args) {
        List<Edge> edgeList = new ArrayList<>();

        edgeList.add(new Edge(0, 1, 10));
        edgeList.add(new Edge(0, 2, 3));
        edgeList.add(new Edge(2, 1, 1));
        edgeList.add(new Edge(1, 2, 4));
        edgeList.add(new Edge(1, 3, 2));
        edgeList.add(new Edge(2, 4, 2));
        edgeList.add(new Edge(2, 3, 8));
        edgeList.add(new Edge(3, 4, 7));
        edgeList.add(new Edge(4, 3, 9));

        Graph graph = new Graph(edgeList, 5);

        BellmanFord d = new BellmanFord(graph);

        d.shortestPath(0, 5);
    }
}

class BellmanFord {

    Graph graph; 

    public BellmanFord(Graph graph) {
        this.graph = graph;
    }

    public void shortestPath(int root, int numberOfVertices) {
       
        // Belllman ford examines all edges |V| - 1 times
        // while relaxin them until there can no longer be further relxation. 
        // also detecs a negative cycle where possible 

        int[] distances = new int[numberOfVertices];
        int[] previousVertex = new int[numberOfVertices];

        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(previousVertex, -1);

        distances[root] = 0;

        for (int i = 0; i < numberOfVertices - 1; i++) {
            for (Edge edge: this.graph.edgeList) {

                int source = edge.source;
                int dest = edge.dest; 
                int weight = edge.weight;

                if (distances[dest] <= weight + distances[source]) {
                    continue;
                }

                // relaxation
                 distances[dest] = weight + distances[source];

                 previousVertex[dest] = source;
            }
        }

        if (this.hasNegativeCycle(distances)) {
            throw new IllegalArgumentException();
        }

        this.printPath(root, distances, previousVertex);
    }

    public boolean hasNegativeCycle(int[] distances)
    {
        for (Edge edge : this.graph.edgeList) {
            int weight = edge.weight;

            if (distances[edge.dest] > weight + distances[edge.source]) {
                return true;
            }
        }

        return false;
    }
    
    private void printPath(int root, int[] distances, int[] previousVertex) {

        for (int i = 0; i < distances.length; i++) {

            if (i != root) {
                System.out.println(i);
                System.out.println(distances[i]);

                List<Integer> result =  new ArrayList<>();
                
                this.getRoute(root, i, previousVertex, result);

                System.out.println(Arrays.toString(result.toArray()));
                System.out.println("B r e a k ..");
            }
        }
    }

    private void getRoute(int root, int destination, int[] previous, List<Integer> result) {

        if (destination == previous[root]) {
            return;
        }

        result.add(0, destination);
        getRoute(root, previous[destination], previous, result);
    }
}

class Graph {

    List<Edge> edgeList;
    int numberOfVertices;

    public Graph(List<Edge> edgeList, int numberOfVertices) {
        this.edgeList = edgeList;
        this.numberOfVertices = numberOfVertices;
    }
}

class Edge {
    int source; 
    int dest; 

    int weight;

    public Edge(int source, int dest, int weight) {

        this.source = source;
        this.dest = dest;

        this.weight = weight;
    }
}
