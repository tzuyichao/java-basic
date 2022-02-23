package brute;

/**
 * 63. Unique Paths II
 * https://leetcode.com/problems/unique-paths-ii/
 *
 * Runtime: 1 ms, faster than 59.46% of Java online submissions for Unique Paths II.
 * Memory Usage: 42.4 MB, less than 14.15% of Java online submissions for Unique Paths II.
 */
public class UniquePaths2 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        var dp = new int[m][n];

        if(obstacleGrid[0][0] == 1) {
            dp[0][0] = 0;
        } else {
            dp[0][0] = 1;
        }
        for(int i=1; i<n; i++) {
            if(obstacleGrid[0][i] == 1) {
                dp[0][i] = 0;
            } else {
                dp[0][i] = dp[0][i - 1];
            }
        }
        for(int i=1; i<m; i++) {
            if(obstacleGrid[i][0] == 1) {
                dp[i][0] = 0;
            } else {
                dp[i][0] = dp[i-1][0];
            }
        }
        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                if(obstacleGrid[i][j] != 1) {
                    var up = obstacleGrid[i-1][j] != 1?dp[i-1][j]:0;
                    var left = obstacleGrid[i][j-1] != 1?dp[i][j-1]:0;
                    dp[i][j] = up + left;
                }
            }
        }

        return (obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1)?0:dp[m-1][n-1];
    }
}
