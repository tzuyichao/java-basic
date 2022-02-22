package brute;

/**
 * 62. Unique Paths
 * https://leetcode.com/problems/unique-paths/
 *
 * Runtime: 1 ms, faster than 46.95% of Java online submissions for Unique Paths.
 * Memory Usage: 41.3 MB, less than 9.14% of Java online submissions for Unique Paths.
 *
 * Runtime: 1 ms, faster than 46.95% of Java online submissions for Unique Paths.
 * Memory Usage: 40.8 MB, less than 25.98% of Java online submissions for Unique Paths.
 *
 */
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        var dp = new int[m][2];
        for(int i=0; i<m; i++) {
            dp[i][0] = 1;
        }
        for(int i=0; i<dp[0].length; i++) {
            dp[0][i] = 1;
        }

        for(int i=1; i<n; i++) {
            for(int j=1; j<m; j++) {
                if(i%2 == 1) {
                    dp[j][1] = dp[j][0] + dp[j - 1][1];
                } else {
                    dp[j][0] = dp[j][1] + dp[j - 1][0];
                }
            }
        }

        return dp[m-1][(n+1)%2];
    }
}
