import java.util.*; 

class SubSets {

    public static List<List<Integer>> distinctSubsets(int[] items)
    {
        /**
         * Given a set with distinct elements, find all of its distinct subsets.

        Example 1:

        Input: [1, 3, 4]
        Output: [], [1], [3], [1,3]

        [...]
        []
        [[]]
        1,3,4
        [[], [1]]
        [[], [1], [3], [1,3]]
        [[],[1],[3],[1,3], [4], [1,4], [3,4], [1,3,4]]

         */

         List<List<Integer>> aList = new ArrayList<>();

         aList.add(new ArrayList<>());

         for (int i = 0; i < items.length; i++) {

            int currentListLength = aList.size(); 

             for (int j = 0; j < currentListLength; j++) {
                 List<Integer> newList = new ArrayList<>(aList.get(j));
                 newList.add(items[i]);

                 aList.add(newList);
             }
         }

         return aList; 
    }

    public static List<String> stringPermutationsByChangingCase(String s) {
        /**
         *  Given a string, find all of its permutations preserving the character sequence but changing case.

            Example 1:

            Input: "ad52"
            Output: "ad52", "Ad52", "aD52", "AD52" 
         */

         List<String> permutations = new ArrayList<>();

         if (s == null) return permutations;

         permutations.add(s);

         for (int i = 0; i < s.length; i++) {
            if (Character.isLetter(s.charAt(i))) {
                int currentSize = permutations.size();
                for (int j = 0; j < currentSize; j++) {
                    char[] stringToChars = permutations.get(j).toCharArray();

                    if (Character.isLowerCase(stringToChars[i])) {
                        stringToChars[i] = Character.toUpperCase(stringToChars[i]);
                    } else {
                        stringToChars[i] = Character.toLowerCase(stringToChars[i]);
                    }

                    permutations.add(String.valueOf(stringToChars));
                }
            }
         }

         return permutations; 
    }

    public static void main(String[] args) {

        List<List<Integer>> result = SubSets.distinctSubsets(new int[] { 1, 3 });
        System.out.println("Here is the list of subsets: " + result);
    
        result = SubSets.distinctSubsets(new int[] { 1, 5, 3 });
        System.out.println("Here is the list of subsets: " + result);
    }

}