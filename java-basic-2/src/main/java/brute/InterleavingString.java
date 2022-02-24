package brute;

/**
 * 97. Interleaving String
 * https://leetcode.com/problems/interleaving-string/
 *
 * 我自己現在只知道要用dp，但想不出來這樣的dynamic programming state change作法
 *
 * Runtime: 4 ms, faster than 67.96% of Java online submissions for Interleaving String.
 * Memory Usage: 42.4 MB, less than 26.99% of Java online submissions for Interleaving String.
 */
public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        var m = s1.length();
        var n = s2.length();
        if(m+n != s3.length()) {
            return false;
        }
        var dp = new boolean[m+1][n+1];

        dp[0][0] = true;
        for(int i=1; i<dp.length; i++) {
            dp[i][0] = dp[i-1][0] && (s1.charAt(i-1) == s3.charAt(i-1));
        }

        for(int i=1; i<dp[0].length; i++) {
            dp[0][i] = dp[0][i-1] && (s2.charAt(i-1) == s3.charAt(i-1));
        }

        for(int i=1; i<dp.length; i++) {
            for(int j=1; j<dp[0].length; j++) {
                dp[i][j] = (dp[i-1][j] && (s1.charAt(i-1) == s3.charAt(i+j-1))) || (dp[i][j-1] && (s2.charAt(j-1) == s3.charAt(i+j-1)));
            }
        }

        return dp[m][n];
    }
}
