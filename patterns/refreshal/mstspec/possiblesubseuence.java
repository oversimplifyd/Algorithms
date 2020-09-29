import java.util.*; 

class PossibleSubsequence {

    public static List<String> subsequence(String s) {

        /**
         * Given a string, generate all possible subsequences of the string.

            For example, given the string xyz, return an array or set with the following strings:


            x
            y
            z
            xy
            xz
            yz
            xyz
            Note that zx is not a valid subsequence since it is not in the order of the given string.

            xyz
            [""]
            ["", "x"]
            ["", "x", "y", "xy"]
         */

         List<String> result = new ArrayList<>();

         result.add("");

         for (int i = 0; i < s.length(); i++) {

            int resultSize = result.size();

             for (int j = 0; j < resultSize; j++) {
                 StringBuilder  currentString = new StringBuilder(result.get(j));
                 currentString.append(s.charAt(i));

                 result.add(currentString.toString());
             }
         }

         return result;
    }

    public static void main(String[] args) {
        System.out.println(subsequence("xyz"));
    }
}