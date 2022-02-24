package brute;

import java.util.List;

/**
 * 120. Triangle
 * https://leetcode.com/problems/triangle/
 *
 * Runtime: 2 ms, faster than 94.05% of Java online submissions for Triangle.
 * Memory Usage: 44.7 MB, less than 10.25% of Java online submissions for Triangle.
 */
public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        int m = triangle.size();
        int n = triangle.get(m-1).size();
        var dp = new int[m][n];

        dp[0][0] = triangle.get(0).get(0);
        for(int i=1; i<m; i++) {
            dp[i][0] = triangle.get(i).get(0) + dp[i-1][0];
        }

        for(int i=1; i<n; i++) {
            dp[i][i] = triangle.get(i).get(i) + dp[i-1][i-1];
        }

        for(int i=2; i<m; i++) {
            for(int j=1; j<i; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j-1]) + triangle.get(i).get(j);
            }
        }

        int res = Integer.MAX_VALUE;
        for(int i=0; i<n; i++) {
            if(dp[m-1][i] < res) {
                res = dp[m-1][i];
            }
        }

        return res;
    }
}
