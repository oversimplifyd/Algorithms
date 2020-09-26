import java.util.*; 

class WordBreak {

    /**
     *  Given a dictionary of words and a string made up of those words (no spaces), return the original sentence in a list. If there is more than one possible reconstruction, return any of them. If there is no possible reconstruction, then return null.
        For example, given the set of words 'quick', 'brown', 'the', 'fox', and the string "thequickbrownfox", you should return ['the', 'quick', 'brown', 'fox'].
        Given the set of words 'bed', 'bath', 'bedbath', 'and', 'beyond', and the string "bedbathandbeyond", return either ['bed', 'bath', 'and', 'beyond] or ['bedbath', 'and', 'beyond'].

        Approach 1: 
        thequickbrownfor 
        t               h           e       q
        th              he          eq      qu
        the             heq         equ     qui
        theeq....       hequ...     equi..  quic....

        the quick brown for 
        the     -> 1 
        quick   -> 1
        brown   -> 1
        for     -> 1
     */

     public static List<String> matches(Map<String, Integer> map, String s) {

        List<String> result = new ArrayList<>();
        matchRecursive(map, s, result, new HashSet<String>());

        return result;
     }

     /**
      * TOP-DOWN Approach ... DP. 
      */
     private static void matchRecursive(Map<String, Integer> map, String s, List<String> output, HashSet<String> dp) {

        if (s.length() == 0) return;

         for (int i = 1; i <= s.length(); i++) {

            String prefix = s.substring(0, i);  // t, th, the, theq, thequi .... 

            if (map.containsKey(prefix)) {
                // recursive call without t, since it doesnt belong to map 
                output.add(prefix);

                String sub = s.substring(i);

                if (!dp.contains(sub)) { // Memoizing 
                    dp.add(sub);
                    matchRecursive(map, s.substring(i), output, dp);
                }
            } 
         }
     }

     public static void main(String[] args) {
         Map<String, Integer> map = new HashMap<>();
         map.put("bed", 0);
         map.put("bath", 0);
         map.put("bedbath", 0);
         map.put("and", 0);
         map.put("beyond", 0);

         System.out.println(matches(map, "bedbathandbeyond"));
     }
}
