package brute;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 994. Rotting Oranges
 * https://leetcode.com/problems/rotting-oranges/
 *
 * https://www.cnblogs.com/grandyang/p/14257283.html
 */
public class RottingOranges {
    static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        static RottingOranges.Pair of(int x, int y) {
            return new RottingOranges.Pair(x, y);
        }
    }

    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dirs = new int[][] {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        int res = 0;
        int freshLeft = 0;
        Queue<Pair> q = new ArrayDeque<>();

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(grid[i][j] == 1) ++freshLeft;
                else if(grid[i][j] == 2) q.offer(Pair.of(i, j));
            }
        }

        while(!q.isEmpty() && freshLeft > 0) {
            for(int i=q.size(); i>0; i--) {
                Pair pair = q.poll();
                for(int[] dir : dirs) {
                    int x = pair.x + dir[0];
                    int y = pair.y + dir[1];
                    if(x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != 1) continue;
                    grid[x][y] = 2;
                    q.offer(Pair.of(x, y));
                    freshLeft--;
                }
            }
            res++;
        }

        return freshLeft > 0?-1:res;
    }
}
