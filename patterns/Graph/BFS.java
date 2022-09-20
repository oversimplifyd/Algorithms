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

        BFS solution = new BFS(graph);

        solution.traverse();
    }
}

class BFS {

    boolean[] discovered;
    Graph graph;

    public BFS(Graph graph) {
        this.graph = graph;
    }

    public void traverse() {

        Deque<Integer> queue = new ArrayDeque<>();
        boolean[] discovered = new boolean[this.graph.adjacencyList.size()];

        for (int i = 0; i < this.graph.adjacencyList.size(); i++ ) {

            if (discovered[i]) {
                continue;
            }

            discovered[i] = true;
            queue.add(i);

            while (!queue.isEmpty()) {
                int vertex = queue.poll();
                List<Integer> neighbours = this.graph.adjacencyList.get(vertex);
                
                System.out.println(vertex);

                for (int neighbour: neighbours) {
                    if (!discovered[neighbour]) {
                        queue.add(neighbour);
                        discovered[neighbour] = true;
                    }
                }
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