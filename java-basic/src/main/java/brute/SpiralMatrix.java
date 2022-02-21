package brute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * = 54. Spiral Matrix
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Spiral Matrix.
 * Memory Usage: 42.4 MB, less than 7.44% of Java online submissions for Spiral Matrix.
 */
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        if(m == 0 || n == 0) {
            return Collections.EMPTY_LIST;
        }
        List<Integer> result = new ArrayList<>();
        int c = m>n?(n+1)/2:(m+1)/2;
        int p = m;
        int q = n;
        for(int i=0; i<c; i++, q-=2, p-=2) {
            for(int col=i; col<i+q; col++) {
                result.add(matrix[i][col]);
            }
            for(int row=i+1; row<i+p; row++) {
                result.add(matrix[row][i+q-1]);
            }
            if(q == 1 || p == 1) {
                break;
            }
            for(int col=i+q-2; col >= i; col--) {
                result.add(matrix[i+p-1][col]);
            }
            for(int row=i+p-2; row>i; row--) {
                result.add(matrix[row][i]);
            }
        }

        return result;
    }
}
