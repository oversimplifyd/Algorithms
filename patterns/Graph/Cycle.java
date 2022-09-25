import java.util.*;

class Solution {

    // Union runtime -> O(n ^ 2) 
    // the Union Rank optimization improves this to (n * log n)
    
    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();
        
        edges.add(new Edge(1, 2));
        edges.add(new Edge(1, 3));
        edges.add(new Edge(3, 4));
        edges.add(new Edge(3, 5));
        edges.add(new Edge(4, 5));

        System.out.println(
            new GraphCycle(
                new Graph(edges)
                ).hasCycle());
    }
}

class GraphCycle {

    Graph graph; 

    HashMap<Integer, Integer> set; 

    public GraphCycle(Graph graph) {
        this.graph = graph;
        this.set = new HashMap<>();

        // make set
        for (Edge e: this.graph.edgeList) {
            this.set.put(e.source, e.source);
            this.set.put(e.dest, e.dest);
        }
    }

    public boolean hasCycle() {

        for (Edge e: this.graph.edgeList) {
            if (this.union(e.source, e.dest)) {
                return true;
            }
        }

        return false;
    }

    private int find(int vertex) {

        if (this.set.get(vertex) != vertex) {
            // path compression  
            this.set.put(vertex, this.find(this.set.get(vertex)));
        }

        return this.set.get(vertex);
    }

    private boolean union(int vertexA, int vertexB) {
        int x = this.find(vertexA);
        int y = this.find(vertexB);

        if (x == y) {
            // has cycle
            return true;
        }

        this.set.put(x, y);

        return false;
    }
    
}


class Graph {

    List<Edge> edgeList; 

    public Graph(List<Edge> edgeList) {
        this.edgeList = edgeList;
    }
}

class Edge {

    int source; 
    int dest; 

    int weight; 

    public Edge(int source, int dest) {
        this.source = source;
        this.dest = dest;
    }
}