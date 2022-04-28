package brute;

/**
 * 1631. Path With Minimum Effort
 * https://leetcode.com/problems/path-with-minimum-effort/
 *
 * Runtime: 373 ms, faster than 5.43% of Java online submissions for Path With Minimum Effort.
 * Memory Usage: 53.6 MB, less than 72.64% of Java online submissions for Path With Minimum Effort.
 *
 * https://zxi.mytechroad.com/blog/graph/leetcode-1631-path-with-minimum-effort/
 */
public class PathWithMinimumEffort {
    public int minimumEffortPath(int[][] heights) {
        var r = heights.length;
        var c = heights[0].length;
        int[][] dp = new int[r][c];
        int[] dirs = new int[] {0, -1, 0, 1, 0};

        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[0][0] = 0;
        for(int k=0; k<r*c; k++) {
            boolean isStable = true;

            for(int i=0; i<r; i++) {
                for(int j=0; j<c; j++) {
                    for(int d=0; d<4; d++) {
                        int tx = j + dirs[d];
                        int ty = i + dirs[d + 1];
                        if(tx < 0 || tx == c || ty < 0 || ty == r) {
                            continue;
                        }
                        int t = Math.max(dp[ty][tx], Math.abs(heights[ty][tx] - heights[i][j]));

                        if(t < dp[i][j]) {
                            //System.out.println(dp[i][j] + "/" + i + "," + j + "=" + t);
                            isStable = false;
                            dp[i][j] = t;
                            //System.out.println(dp[i][j] + "/" + i + "," + j + "=" + t);
                        }
                    }
                }
            }

            if(isStable) {
                break;
            }
        }

        return dp[r-1][c-1];
    }
}
