import java.util.*; 

class StringPatternOccurrence {

    public static List<Integer> occurence(String s, String pattern){

        if (pattern.length() == 0 || s.length() == 0) throw new IllegalArgumentException("Invalid input");

        // for case senstivity, we want to convert both strings to lower case before checking 
        
        /**
         * Given a string and a pattern, find the starting indices of all occurrences 
         * of the pattern in the string. For example, given the string "abracadabra" 
         * and the pattern "abr", you should return [0, 7].
         *   
         * 
         *  abracadabra         abr
         *  
         * 
         */

         List<Integer> matches = new ArrayList<>();
         boolean matched = true; 

         // O(N*patteernLength)
         for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == pattern.charAt(0) && s.length() - i >= pattern.length()) {
                int k = 0; 
                for (int j = i; j < i + pattern.length(); j++) {
                    if (s.charAt(j) != pattern.charAt(k)) {
                        matched = false; 
                        break; 
                    }
                    k++;
                }
                if (matched) matches.add(i);
                matched = true;
            }
         }

         return matches;
    }


    public static void main(String[] args) {

        System.out.println(occurence("abracadabra", "b"));
    }
}