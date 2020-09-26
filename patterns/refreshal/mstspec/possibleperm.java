import java.util.*; 

class PossiblePermutations {

    /**
     * 
     * Time Taken: 47 mins 
     * 
     * Follow-Up: About if we have duplicates number in the list? 
     *  Answer: I will create a Set consisting of the hash of the numbers concatenated to string. 
     * 
     * Given a number in the form of a list of digits, return all possible permutations.

        For example, given [1,2,3], return [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]].

        [1,2,3,3]
        [[]]
        [[1]]
        [[2,1], [1,2]]
        [3,2,1], [2,3,1], [2,1,3]
        [3,1,2], [1,3,2], [1,2,3]
        3,3,1,2 3,3,1,2 3,1,3,2 3,1,2,3

        // If the original set contains duplicates, 
        // Add the digits as chars, oconverted to string stored in  a set 
     */

     public static List<List<Integer>> permutations(int[] items) {
        Queue<List<Integer>> permutations = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
   
        permutations.add(new LinkedList<Integer>());
   
        for(int number: items) {
            // [[]]
            // [[1]]
            //[[1]]

            // [2,1]
            // [1,2]

            //3 2 1 
            // 2 3 1
            // 2 1 3 

            // 3 1 2 
            // 1 3 2 
            // 1 2 3 

            int currentSize = permutations.size();

            for (int i = 0; i < currentSize; i++) {

                List<Integer> currentList = permutations.poll();

                for (int j = 0; j <= currentList.size(); j++) {

                    List<Integer> newList = new LinkedList<Integer>(currentList);
                    newList.add(j, number);
    
                    if (newList.size() != items.length) {
                        permutations.offer(newList);
                    } else {
                        result.add(newList);
                    }
                }

            }

        }

        return result; 
     }

     public static void main(String[] args) {
         int[] items = new int[]{1, 4, 2, 3};

         System.out.println(permutations(items));
     }
}