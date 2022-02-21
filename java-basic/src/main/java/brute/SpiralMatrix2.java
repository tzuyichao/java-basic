package brute;

/**
 * 59. Spiral Matrix II
 *
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Spiral Matrix II.
 * Memory Usage: 42.1 MB, less than 15.77% of Java online submissions for Spiral Matrix II.
 */
public class SpiralMatrix2 {
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];

        int c = n+1/2;
        int p = n;
        int idx = 1;
        for(int i=0; i<c; i++, p-=2) {
            for(int col=i; col<i+p; col++) {
                result[i][col] = idx++;
            }
            for(int row=i+1; row<i+p; row++) {
                result[row][i+p-1] = idx++;
            }
            if(p == 1) {
                break;
            }
            for(int col=i+p-2; col>=i; col--) {
                result[i+p-1][col] = idx++;
            }
            for(int row=i+p-2; row>i; row--) {
                result[row][i] = idx++;
            }
        }

        return result;
    }
}
