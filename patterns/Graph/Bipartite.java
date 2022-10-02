import java.util.*;

class Solution {
    public static void main(String[] args) {
        List<Edge> edges = Arrays.asList(
            new Edge(0, 1), new Edge(1, 2), new Edge(1, 7), new Edge(2, 3),
            new Edge(3, 5), new Edge(4, 6), new Edge(4, 8), new Edge(7, 8)
        );

        Graph graph = new Graph(edges, 9);
        BiPartite bp = new BiPartite(graph);

        System.out.println(bp.check());
    }
}

/**
 * An undirected graph is bipartite if it can be bi partitioned into two set such that 
 * an edge goes from one set and ends in the other, and there are  no adjancet connections. 
 * i.e. no edge goes from one vertex to another in the same set. 
 * 
 * A bipartitie graph is also said to be two colorable. 
 * A graph is two colorable if each edge has different colors at both ends. 
 * 
 * Properties: 
 * All acyclic graphs are bi-partite 
 * For a cyclic graph to be biparitite, the cycle has to contain even numbered edges. 
 * A graph with an old numbered edge cycle is not bi-partitie. 
 */
class BiPartite {

    Graph graph;

    public BiPartite(Graph graph) {
        this.graph = graph;
    }

    public boolean check() {

        int[] bfsLevel = new int[this.graph.numberOfVerticies];
        Deque<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[this.graph.numberOfVerticies];

        int startVertex = 0;

        visited[startVertex] = true;
        bfsLevel[startVertex] = 0;

        queue.add(startVertex);

        while(!queue.isEmpty()) {
            int vertex = queue.poll();
            List<Integer> neighbours = this.graph.adjList.get(vertex);

            for (int i: neighbours) {
                if (! visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                    bfsLevel[i] = bfsLevel[vertex] + 1;
                } else {
                    if (bfsLevel[vertex] == bfsLevel[i]) {
                        return false;
                    }
                }
            }
        }

        return true;
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
            this.adjList.get(e.dest).add(e.source);
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
