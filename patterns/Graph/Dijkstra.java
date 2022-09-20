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

        Dijkstra d = new Dijkstra(graph);

        d.getRoute(0, 1);
    }
}

class Dijkstra {

    Graph graph; 

    public Dijkstra(Graph graph) {
        this.graph = graph;
    }

    private void shortestPath(int root, int destination) {
        PriorityQueue<Node> minHeap = new PriorityQueue<>(
            this.graph.adjList.size(), (a,b) -> a.distance - b.distance);
        
        int numberOfVertices = this.graph.numberOfVertices;

        boolean[] picked = new boolean[numberOfVertices];
        List<Integer> distances = new ArrayList<>(Collections.nCopies(numberOfVertices, Integer.MAX_VALUE));
        distances.set(root, 0);

        picked[root] = true;

        int[] previousVertex = new int[numberOfVertices];
        previousVertex[root] = -1;

        Node source = new Node(root, 0);

        minHeap.offer(source);

        while (!minHeap.isEmpty()) {
            Node current = minHeap.poll();

            List<Edge> outGoingEdges = this.graph.adjList.get(current.value);

            for (Edge edge: outGoingEdges) {

                int dest = edge.dest; 
                int weight = edge.weight;

                if (picked[dest]) {
                    continue ;
                }

                int newDistance = distances.get(current.value) + edge.weight;
                int prevDistane = distances.get(dest);

                if (newDistance >= prevDistane) {
                    continue;
                }

                distances.set(dest, newDistance);

                Node newNode = new Node(dest, distances.get(dest));

                minHeap.offer(newNode);

                previousVertex[dest] = current.value;
            }

            picked[current.value] = true;
        }
        
        this.printAllPath(distances, previousVertex, root);
    }

    private void printAllPath(List<Integer> distances, int[]  prevVertex, int source) {
        for (int vertex = 0; vertex < distances.size(); vertex++) {
            if (vertex != source) {
                int distance = distances.get(vertex);
                this.printRoute(prevVertex, vertex);
                System.out.print("B R E A K ....");
            }
        }
    }

    private void printRoute(int[] previousVertex, int current) {

        if (current == -1) {
            return;
        }

        System.out.print(current);
        System.out.print(",");
        System.out.print(previousVertex[current]);
        System.out.print(",");

        this.printRoute(previousVertex, previousVertex[current]);
    }


    public void getRoute(int source, int dest){
        this.shortestPath(source, dest);
    }
}

class Graph {

    List<Edge> edgeList;
    int numberOfVertices;

    List<List<Edge>> adjList = null;

    public Graph(List<Edge> edgeList, int numberOfVertices) {
        this.edgeList = edgeList;
        this.numberOfVertices = numberOfVertices;

        this.adjList = new ArrayList<>();

        for (int i = 0; i < numberOfVertices; i++) {
            this.adjList.add(new ArrayList<>());
        }

        // undirected graph.
        for (Edge e: this.edgeList) {
            this.adjList.get(e.source).add(e);
        }
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

class Node {

    int value; 

    int distance;

    public Node(int val, int distance) {
        this.value = val;
        this.distance = distance;
    }
}
