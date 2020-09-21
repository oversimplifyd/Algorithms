import java.util.*; 

class TopologicalSort {

    public static List<Integer> sort(int vertices, int[][] edges){

        /**
         * Topological Sort of a directed graph (a graph with unidirectional edges) is a linear ordering of its vertices such that for every directed edge (U, V) from vertex U to vertex V, U comes before V in the ordering.

            Given a directed graph, find the topological ordering of its vertices.

            Example 1:

            Input: Vertices=4, Edges=[3, 2], [3, 0], [2, 0], [2, 1]
            Output: Following are the two valid topological sorts for the given graph:
            1) 3, 2, 0, 1
            2) 3, 2, 1, 0

            The General Idea behind a topoligacal sort is simple: 
            We want to be able to build the vertices in a topological order such that 
            each parent comes before a child. 

            Parent -> Child relationship is represented as edges an aray 
            Topological sort can only be done on a DAG 
            Directed Acyclic Graph 

            Solution Steps: 
            Step 1: Build an Adjacency list -> Parent -> [Children]
            Step 2: Build an indigree list, indigree is the number of incoming edges for a vertex 
            Step 3: Add the Sources (Vertex with 0 degress) to a Queue 
            Step 4: Apply BFS on the Surce List, such that the sources are added to the sorted order and any new sources are added back to queue 

            A graph has a cycle when the sortedOrderList length isnot equal to the ertices 
            if the vertices is = return empty list 

            A sunk is vertex wth no outgoing edge 
         */

         List<Integer> sortedOrder = new ArrayList<>();

         if (vertices <= 0) return sortedOrder;

         // Build the Graph and Indigrees 
         Map<Integer, List<Integer>> graph = new HashMap<>();
         Map<Integer, Integer> indigrees = new HashMap<>();

         for (int i = 0; i < vertices; i++) {
             // 0 - 5 
             graph.put(i, new ArrayList<Integer>());
             indigrees.put(i, 0);
         }

         for (int i = 0; i < edges.length; i++) {
             int parent = edges[i][0];
             int child = edges[i][1];

             graph.get(parent).add(child);
             indigrees.put(child, indigrees.get(child) + 1);
         }

         Queue<Integer> queue = new LinkedList<Integer>();

         for (Map.Entry<Integer, Integer> entry: indigrees.entrySet()){
             if (entry.getValue() == 0 ) {
                 //It's a source 
                 queue.add(entry.getKey());
             }
         }

         while (!queue.isEmpty()) {
             int currentSource = queue.poll();
             List<Integer> children = graph.get(currentSource);

             sortedOrder.add(currentSource);

             for (int child: children) {
                 //Reduce the indigree of the children 
                 indigrees.put(child, indigrees.get(child) - 1);

                 if (indigrees.get(child) == 0) {
                     // now a source, since all its parent or incoming edges have been redued or taken 
                     queue.add(child);
                 }
             }
         }

         if (sortedOrder.size() != vertices) {
             // There's a cycle, Damn it! 
             return new ArrayList<>();
         }

         return sortedOrder;
    }

    public static void main(String[] args) {
        List<Integer> result = TopologicalSort.sort(4,
        new int[][] { new int[] { 3, 2 }, new int[] { 3, 0 }, new int[] { 2, 0 }, new int[] { 2, 1 } });
        System.out.println(result);

        result = TopologicalSort.sort(5, new int[][] { new int[] { 4, 2 }, new int[] { 4, 3 }, new int[] { 2, 0 },
            new int[] { 2, 1 }, new int[] { 3, 1 } });
        System.out.println(result);

        result = TopologicalSort.sort(7, new int[][] { new int[] { 6, 4 }, new int[] { 6, 2 }, new int[] { 5, 3 },
            new int[] { 5, 4 }, new int[] { 3, 0 }, new int[] { 3, 1 }, new int[] { 3, 2 }, new int[] { 4, 1 } });
        System.out.println(result);
    }
}