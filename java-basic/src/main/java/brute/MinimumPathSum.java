package brute;

/**
 * 64. Minimum Path Sum
 *
 * Runtime: 2 ms, faster than 88.76% of Java online submissions for Minimum Path Sum.
 * Memory Usage: 45.9 MB, less than 20.71% of Java online submissions for Minimum Path Sum.
 */
public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] solver = new int[m][n];

        solver[0][0] = grid[0][0];
        for(int i=1; i<n; i++) {
            solver[0][i] = solver[0][i-1] + grid[0][i];
        }
        for(int i=1; i<m; i++) {
            solver[i][0] = solver[i-1][0] + grid[i][0];
        }
        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                solver[i][j] = Math.min(solver[i-1][j], solver[i][j-1]) + grid[i][j];
            }
        }

        return solver[m-1][n-1];
    }
}
