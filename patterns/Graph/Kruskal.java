import java.util.*;

class Solution {
    public static void main(String[] args) {
        List<Edge> edgeList = Arrays.asList(
            // new Edge(0, 1, 1), // a - c
            // new Edge(0, 2, 2),  // a - f
            // new Edge(0, 3, 4),  // a - b
            // new Edge(2, 4, 3), // f -> d
            // new Edge(4, 5, 8), // d - e 
            // new Edge(5,1, 7), // e - c 
            // new Edge(4, 3, 6), // b - c 
            // new Edge(3, 1, 5) // b - c 
            new Edge(0, 1, 7), 
            new Edge(1, 2, 8), 
            new Edge(0, 3, 5),
            new Edge(1, 3, 9), 
            new Edge(1, 4, 7), 
            new Edge(2, 4, 5),
            new Edge(3, 4, 15), 
            new Edge(3, 5, 6), 
            new Edge(4, 5, 8),    
            new Edge(4, 6, 9), 
            new Edge(5, 6, 11)
        );

        Kruskal k = new Kruskal(edgeList, 7);
        List<List<Integer>> res = k.mst();

        for (int i = 0; i < res.size(); i++) {
            System.out.println(i);
            System.out.println(Arrays.toString(res.get(i).toArray()));
        }

        System.out.println(k.totalCost);
    }
}

class Kruskal {

    /**
     * Kruskal solves the minimum spanning tree problem in a weighted undirected graph. 
     * A minimum spanning tree is one which given |V| vertices, connect all vertices using |V| - 1 edges. 
     * That is, how can I get the least cost graph of out a weighed graph using only |V| - 1 edges. 
     * 
     * Kruskal approach: 
     * - Re-order edges into a data structure where we can get alwasy get a subsequent minimum, 
     * - connect the vertices if there are no cycle.
     * - pick the next edge if we find a cycle. 
     */

     List<Edge> edgeList; 
     List<List<Integer>> adjList; 
     DisjointSet ds;
     int numberOfVertices;

     int totalCost = 0;

     PriorityQueue<Edge> minHeap = new PriorityQueue<>((a,b) -> a.weight - b.weight);

     public Kruskal(List<Edge> edges, int numberOfVertices) {
         this.adjList = new ArrayList<>(numberOfVertices);
         this.edgeList = edges;
         this.ds = new DisjointSet(this.edgeList);

         for (Edge e: this.edgeList) {
            this.minHeap.offer(e);  // E (Log E)
         }

         for (int i = 0; i < numberOfVertices; i++) {
             this.adjList.add(new ArrayList<>());
         }
         
         this.numberOfVertices = numberOfVertices;
     }

     public List<List<Integer>> mst(){

        int processedEdges = 0;

        while (!this.minHeap.isEmpty()) {
            Edge e = this.minHeap.poll();

            // O(n^2) 
            // with rank  optimization O(n * log n)
            if (!this.ds.union(e.source, e.dest)) {
                continue;
            }
            
            List<Integer> currentEdges;

            if (this.adjList.get(e.source).size() != 0) {
                 currentEdges = this.adjList.get(e.source);
            } else {
                currentEdges = new ArrayList<>();
            }

            currentEdges.add(e.dest);
            this.adjList.set(e.source, currentEdges);
            processedEdges++;

            this.totalCost += e.weight;

            if (processedEdges == this.numberOfVertices - 1) {
                return this.adjList;
            }
        }

        return this.adjList;
     }
}

class DisjointSet {

    private List<Edge> edgeList; 
    private HashMap<Integer, Integer> setMap = new HashMap<>();

    public DisjointSet(List<Edge> edges) {
        this.edgeList = edges;

        // MakeSet
        for (Edge e: this.edgeList) {
            this.setMap.put(e.source, e.source);
            this.setMap.put(e.dest, e.dest);
        }
    }

    private int find(int vertex) {

        if (this.setMap.get(vertex) != vertex) {

            // path compression
            this.setMap.put(vertex, this.find(this.setMap.get(vertex)));
        }

        return this.setMap.get(vertex);
    }

    public boolean union (int vertexA, int vertexB) {

        int x = this.find(vertexA);
        int y = this.find(vertexB);

        if (x == y) {
            return false;
        }

        this.setMap.put(x, y);

        return true;
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
