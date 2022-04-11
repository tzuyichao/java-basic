package brute;

import java.util.ArrayList;
import java.util.List;

/**
 * 1260. Shift 2D Grid
 * https://leetcode.com/problems/shift-2d-grid/
 *
 * Runtime: 14 ms, faster than 27.52% of Java online submissions for Shift 2D Grid.
 * Memory Usage: 43 MB, less than 87.98% of Java online submissions for Shift 2D Grid.
 */
public class Shift2DGrid {
    public int[][] shift(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] res = new int[m][n];
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
               if(j == n-1 && i == m-1) {
                   res[0][0] = grid[i][j];
               } else if(j == n-1) {
                   res[i+1][0] = grid[i][j];
               } else {
                   res[i][j+1] = grid[i][j];
               }
            }
        }
        return res;
    }

    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        for(int i=0; i<k; i++) {
            grid = shift(grid);
        }

        List<List<Integer>> res = new ArrayList<>();
        for(int i=0; i<grid.length; i++) {
            List<Integer> row = new ArrayList<>();
            for(int j=0; j<grid[0].length; j++) {
                row.add(grid[i][j]);
            }
            res.add(row);
        }
        return res;
    }
}
