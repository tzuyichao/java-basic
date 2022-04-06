package brute;

/**
 * 221. Maximal Square
 * https://leetcode.com/problems/maximal-square/
 *
 * Runtime: 7 ms, faster than 65.92% of Java online submissions for Maximal Square.
 * Memory Usage: 58.3 MB, less than 52.03% of Java online submissions for Maximal Square.
 */
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m+1][n+1];
        int ans = 0;
        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                if(matrix[i-1][j-1] == '1') {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j-1]);
                    dp[i][j] = Math.min(dp[i][j], dp[i][j-1]) + 1;
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        return ans * ans;
    }
}
