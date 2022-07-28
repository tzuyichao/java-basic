package brute;

/**
 * https://dev.to/seanpgallivan/solution-out-of-boundary-paths-2jef#java-code
 */
public class OutOfBoundaryPaths {
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        if (maxMove == 0) return 0;
        int[][] dpCurr = new int[m+2][n+2], dpLast = new int[m+2][n+2];
        for (int i = 1; i <= m; i++) {
            dpCurr[i][1]++;
            dpCurr[i][n]++;
        }
        for (int j = 1; j <= n; j++) {
            dpCurr[1][j]++;
            dpCurr[m][j]++;
        }
        int ans = dpCurr[startRow+1][startColumn+1];
        for (int d = 1; d < maxMove; d++) {
            int[][] temp = dpCurr;
            dpCurr = dpLast;
            dpLast = temp;
            for (int i = 1; i <= m; i++)
                for (int j = 1; j <= n; j++)
                    dpCurr[i][j] = (int)(((long)dpLast[i-1][j] + dpLast[i+1][j] + dpLast[i][j-1] + dpLast[i][j+1]) % 1000000007L);
            ans = (ans + dpCurr[startRow+1][startColumn+1]) % 1000000007;
        }
        return ans;
    }
}
