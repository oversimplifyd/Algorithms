/**
Let dp(i) be the answer for the string S[i:]. We can calculate dp(i) in terms of dp(i+1) and dp(i+2).

If S[i] == 0, then dp(i) = 0. There are no ways, since no encoded letter starts with 0.

If S[i] == 1, then we have dp(i) = dp(i+1) + dp(i+2), since either we write A plus any way to write S[i+1:], or we write a letter that encodes somewhere between 10 and 19, plus any way to write S[i+2:].

If S[i] == 2, then we have dp(i) = dp(i+1) + (S[i+1] <= 6 ? dp(i+2) : 0). Either we write B plus any way to write S[i+1:], or we write a letter that encodes somewhere between 20 and 26, plus any way to write S[i+2:].

If S[i] > 2, then we have dp(i) = dp(i+1). For example, if S[i] = 5, then we write E plus any way to write S[i+1:]. We can’t write any other letters because the only letter which has encoding that starts with 5 is E.
 */

 // DP Problem O(N) 
import java.io.*;
import java.util.*;

class Solution {

	static int decodeVariations(String S) {
    int[] dpArray = new int[S.length() + 1];
    dpArray[0] = 1; 
    dpArray[1] = S.charAt(0) == '0' ? 0 : 1;
    
    for (int i = 2; i <= S.length(); i++) {
      int oneDigit = Integer.parseInt(S.substring(i-1, i));
      int twoDigit = Integer.parseInt(S.substring(i-2, i));
      
      if (oneDigit > 0) {
        dpArray[i] += dpArray[i-1];
      }
      
      if (twoDigit >= 10 && twoDigit <= 26) {
        dpArray[i] += dpArray[i-2];
      }
    }
    
    return dpArray[S.length()];
  }
  
	public static void main(String[] args) {
    
    System.out.println(decodeVariations("12626"));
	}
}


// 1262 
// max -> 26
// min -> 1 

// 1 2 6 2  1262 / 1000 -> 1 | 1262 % 1000 -> 262 > 262 / 100 -> 2 | 262 % 100 -> 62 -> 10 -> 6 | 62 % 10 2 
// 12 6 2   1262 / 100 -> 12 | 1262 % 100 -> 62 | 62 / 10 -> 6 62 % 10 2 
// 1 26 2   1262 / 10 -> 126 -> 100 | 1 26  , 2 

// n <= 26
// i can only max 2 digits at a time 
// 
