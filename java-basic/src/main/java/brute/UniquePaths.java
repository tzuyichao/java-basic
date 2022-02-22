package brute;

/**
 * 62. Unique Paths
 * https://leetcode.com/problems/unique-paths/
 * Runtime: 1 ms, faster than 46.95% of Java online submissions for Unique Paths.
 * Memory Usage: 41.3 MB, less than 9.14% of Java online submissions for Unique Paths.
 */
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        var dp = new int[m][n];
        for(int i=0; i<m; i++) {
            dp[i][0] = 1;
        }
        for(int i=0; i<n; i++) {
            dp[0][i] = 1;
        }

        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                dp[i][j] = dp[i][j-1] + dp[i-1][j];
            }
        }

        return dp[m-1][n-1];
    }
}
