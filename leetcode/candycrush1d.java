package algorithms; 

/** 
It was a 45 min interview. First he started with my introduction and some prior experience of work for about 10 minutes and asked me why i wanted to join bloomberg. Then told me to open the hackerrank link where he gave me this question:

Write a function to crush candy in one dimensional board. In candy crushing games, groups of like items are removed from the board. In this problem, any sequence of 3 or more like items should be removed and any items adjacent to that sequence should now be considered adjacent to each other. This process should be repeated as many time as possible. You should greedily remove characters from left to right.

Example 1:

Input: "aaabbbc"
Output: "c"
Explanation:
1. Remove 3 'a': "aaabbbc" => "bbbc"
2. Remove 3 'b': "bbbc" => "c"
Example 2:

Input: "aabbbacd"
Output: "cd"
Explanation:
1. Remove 3 'b': "aabbbacd" => "aaacd"
2. Remove 3 'a': "aaacd" => "cd"
 */

import java.util.*; 

class CandyCrush1D  {

    public static String crush(String s) {
        int index = 1; 
        int currentCharCount = 1; 
        while (index <= s.length()) {
            if (index == s.length() || s.charAt(index) != s.charAt(index - 1)) {
                if (currentCharCount >= 3) {
                    if (index == s.length()) {
                        s = s.substring(0, index - currentCharCount);
                    } else {
                        s = s.substring(0, index - currentCharCount) + s.substring(index);
                    }
                    index = 1; 
                    currentCharCount = 1; 
                    continue; 
                }
                currentCharCount = 0; 
            }
            currentCharCount++; 
            index++; 
        }
        return s; 
    }

    public static void main(String[] args) {
        String s = "aaabbbc";
        System.out.println(crush(s));
    }
}
