import java.util.*; 

class RearrangeString {

    /**
     * Given a string s, rearrange the characters so that any two adjacent 
     * characters are not the same. If this is not possible, return null.
       For example, if s = yyz then return yzy. If s = yyy then return null.


       y
       yy
       yyz   -> yzy 
       a a b c d e a a e r f  
       a b a c d e a e a r f 

       a a a a b a a 
       a b a 

       a a b c c d e e e a a e f g a 
       a b a c d c e a e a e a e 

       a a a a b b b b b a c c c c c 

       a b a 

       a a a

       aaaaaaaa 
       a 
       
       y y y y z y y 
       y z y 
       y y y y y    
     */

     public static String rearrangeAdjacent(String scattered) {

        if (scattered.length() <= 1) return scattered;

        Deque<Character> charQueue = new ArrayDeque<>();

        char[] charArray = scattered.toCharArray();
        StringBuilder result = new StringBuilder();

        result.append(charArray[0]);

        int index = 1; 

        while (index < charArray.length) {

            if (charArray[index] == result.charAt(result.length() - 1)) {
                charQueue.add(charArray[index]);
            } else {
                result.append(charArray[index]);
                if (!charQueue.isEmpty()) {
                    result.append(charQueue.poll());
                }
            }

            index++; 
        }

        if (!charQueue.isEmpty()) return ""; 

        return result.toString();
     }

     public static void main(String[] args) {

        System.out.println(rearrangeAdjacent("yyy"));
     }
}