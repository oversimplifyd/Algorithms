import java.util.*;

class LCS {


    public static int lcs(String text1, String text2) {

        // Using DP
        // if x[i] == x[j]  dp[i][j] = dp[i-1][j-1] + 1 // diagonal value + 1 
        // else dp[i][j] = max(dp[i][j-1], dp[i-1][j])
        // return dp[m][n+1]
        // where m and n is the length of x and y respectively 

        int[][] dp = new int[text2.length() + 1][text1.length() + 1];

        for (int i = 0; i <= text1.length(); i++) {
            dp[0][i] = 0;
        }
        
        for (int i = 1; i <= text2.length(); i++) {
            for (int j = 1; j <= text1.length(); j++) {
                if (text2.charAt(i-1) == text1.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }

        return dp[text2.length()][text1.length()];
    }

    public static void main(String[] args){
        System.out.println(lcs("abcbef", "gabonab"));
    }
}
