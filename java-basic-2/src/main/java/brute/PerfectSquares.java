package brute;

import java.util.Arrays;

/**
 * 279. Perfect Squares
 * https://leetcode.com/problems/perfect-squares/
 *
 * ref: https://aaronice.gitbook.io/lintcode/dynamic_programming/perfect-squares
 * Runtime: 62 ms, faster than 49.52% of Java online submissions for Perfect Squares.
 * Memory Usage: 43.6 MB, less than 48.46% of Java online submissions for Perfect Squares.
 * 
 */
public class PerfectSquares {
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i=1; i<=n; i++) {
            for(int j=1; j*j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i-j*j]+1);
            }
        }
        return dp[n];
    }
}
