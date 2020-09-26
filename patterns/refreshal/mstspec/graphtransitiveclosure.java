import java.util.*; 

/**
 * Q U I C K - N O T E S 
 * 
 * Graphs can be represented in several ways 
 * 1 Edge Lists -> An array of edges having two or three items per array 
 *    - two items represent the path from one vertex to the other 
 *     - if there is a third item, this is the weight of the edge, as in a weighted graph 
 *    E.g. [[]0,1], [0,2], [1,3], [3,6]] 
 *         [[0,1,2], [1,2,4]] .... 
 * 
 * 2. Adjacency Matrix: a V x V matrix could also be used to represent a graph. 
 *    - 0s and 1s are assigned to i,j of the matrix if edge(i, j) path exists (1) and if not (0)
 *    - This can also be used to represent weighted graph, by using NULL as non-existing check, 0 as existing check and an actual value as the wiehgt of graph 
 *           0   1    2    3    edge(1,3)  exists, while edge (3, 1) doesn't
 *       0   0   1    0    1
 *       1   1   0    1    0
 *       2   1   0    0    1
 *       3   0   1    1    0
 * 
 * 3. Adjacency List: In an adjacency list, an array of all connected neightbors is given where the total number ofarrays of conected neighbours is equal to the verticies 
 *    E.g. given [[1, 2, 3], [3, 0, 2], [1], [0,1,2], [3]]
 *    This can be re-represented like this: 
 *    0   ->    [1,2,3]
 *    1   ->    [3,0,2]
 *    2   ->    [4]
 *    3   ->    [0,1,2]
 *    4   ->    [1,3]
 *    Essentialy, there can not be more than V neighbours in a ny given adjacency list 
 *    Some vertext may be connected to up to V - 1 vertices 
 *    Some may be connected to no vertices 
 */
class TransitiveClosure {

    /**
     * The transitive closure of a graph is a measure of which vertices are reachable from other vertices. It can be represented as a matrix M, where M[i][j] == 1 if there is a path between vertices i and j, and otherwise 0.

        For example, suppose we are given the following graph in adjacency list form:

        graph = [
            [0, 1, 3],
            [1, 2],
            [2],
            [3]
        ]
        The transitive closure of this graph would be:

        [1, 1, 1, 1]
        [0, 1, 1, 0]
        [0, 0, 1, 0]
        [0, 0, 0, 1]
        Given a graph, find its transitive closure.
     */

     public static int[][] closure(int[][] aList) {
         
        /**
         *  graph = [
            0 -> [0, 1, 3],
            1 -> [1, 2],
            2 -> [2],
            3 -> [3]
        ]

        create a mainResult List 
        foreach of the vertext 
           - create an result  List<> 
           - iteratve over each item in the vertex list 
           - add 1 to the  result list if the index exist else 0 
           - add to main result 
         */

         int[][] result = new int[aList.length][aList.length];

         for (int i = 0; i < aList.length; i++) {

             for (int j = 0; j < aList.length; j++) {
                if (exists(j, aList[i])) {
                    result[i][j] = 1;
                }
             }
         }

         return result;
     }

     private static boolean exists(int needle, int[] items) {
         for (int item: items) {
             if (item == needle) return true; 
         }

         return false;
     }

     public static void main(String[] args) {
         int[][] matrix = {{0, 1, 3, 2},{1, 2},{2},{3}};

         int[][] result = closure(matrix);

        for (int[] array : result) {
            //This will add int values into the new list 
            // and that list will added to the main list
            for (int item: array) {
                System.out.print(item+",");
            }
            System.out.println("");
        }
     }
}