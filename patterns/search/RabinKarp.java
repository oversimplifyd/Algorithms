class Solutio {
    public static void main(String[] args) {
        // System.out.println(new Naive().search("", "banana"));
        // System.out.println(new Naive().search("ban", "banana"));
        System.out.println(new RabinKarp().rabinKarp("banana", "nan"));
    }
}

class RabinKarp {

    /**
     * Rabin Karp: 
     * This unlike Suffix Array, preprocesses the pattern as opposed to preprocessing the text. 
     * This is significantly better when the pattern doesn't change as much as the text does. 
     * 
     * Suffix array / compressed tries is better when the text doesn't change as much as the pattern does. 
     * 
     * How: 
     * The Rabin Karp algorithm aims to solve the string search problem with the use of a rolling hash. 
     * - Take the hash of pattern, 
     * - Take the has of first part of string with pattern length 
     * - compare the hashes, if there is a match, then do an extra check per character to avoid false positives. 
     * - if no match, roll the hash over. Rolling the hash over simply, subtracts the first character and add the next character.
     * - Keep going until window is not large enough for comparison. 
     * 
     * Rabin Karp Hash: 
     * Given a pattern c1....cm (ABCDE)
     * We need to sufficiently compute a hash with a minimal to no collisions. 
     * A bad hash function for example could be adding the integer values of this character. 
     *  A B C D   -> 1 + 2 + 3 + 4 -> 10
     *  This is bad because there are a lot of different combinations of these letters with the same length that gives 10. 
     *  E A A C  -> 5 +  1 + 1 +  3 -> 10 etc... 
     * 
     * Good hash function would be: 
     * (c1X^m-1 + c2X^m-2 + ...+ cm-1X + cm) mod q 
     * 
     * Hash = (c1X^m-1 mod q + c2X^m-2 mod q + ...+ cm-1X mod q + cm mod q)
     * 
     * Hash of next roll would be:  (Current Hash - Hash of first character) x X + next Character 
     * H = (H - c1X^m-1)X + c2 this rolls and shifts the hash leftward. 
     * 
     * where q is a sufficiently large number to avoid integer overflow with pow() 
     * X is a prime number 
     * c1...cm-1 are the characters.
     */

     public boolean rabinKarp(String text, String pattern)
     {
        int pLength = pattern.length();
        int tLength = text.length();

        int constant = 7; 
        int q = 128;

        int pHash = 0; 
        int tHash = 0;

        int multiplier = 1;

        for (int i = 0; i < pLength- 1; i++) {
            multiplier = (constant * multiplier) % q;
        }

        for (int i = 0; i < pLength; i++) {
            pHash = (pHash*constant + pattern.charAt(i)) % q;
            tHash = (tHash*constant + text.charAt(i)) % q;
        }

        int j;

        for (int i = 0; i <= tLength - pLength; i++) {
            if (pHash == tHash) {
                for (j = 0; j < pLength; j++) {
                    if (text.charAt(j+i) != pattern.charAt(j)) break; // no match.
                }

                if (j == pLength) {
                    return true; // returning the first occurence.
                }
            }

            if (i < tLength - pLength) {

                tHash = (((tHash - text.charAt(i) * multiplier) * constant) + text.charAt(i+pLength)) % q;

                if (tHash < 0) {
                    tHash = tHash + q;
                }
            }
        }

        return false;
     }
}

class Naive {

    // Space -> O(1)
    // Time -> O(nm) -> where n is pattern length and m is total number of comparisons or text length. 

    public boolean search(String pattern, String text) {

        int windowStart = 0; 
        int windowEnd = pattern.length() - 1;

        int textLength = text.length();

        while (windowEnd < textLength) {

            if (this.compare(pattern, text, windowStart)) return true;
            
            windowStart++;
            windowEnd++;
        }

        return false;
    }

    public boolean compare(String pattern, String text, int start) {

        int patLength = pattern.length();

        if ((text.length() - start) < patLength) return false;

        for (int i = 0; i < patLength; i++) {
            if (text.charAt(start+i) != pattern.charAt(i)) return false;
        }

        return true;
    }
}