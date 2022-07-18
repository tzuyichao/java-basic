package brute;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 463. Island Perimeter
 * https://leetcode.com/problems/island-perimeter/
 *
 */
public class IslandPerimeter {
    static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        static Pair of(int x, int y) {
            return new Pair(x, y);
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Pair) {
                Pair there = (Pair)obj;
                if(this.x == there.x && this.y == there.y)
                    return true;
            }
            return false;
        }
    }
    public int islandPerimeter(int[][] grid) {
        int[][] dirs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        int res = 0;
        int m = grid.length;
        int n = grid[0].length;
        Queue<Pair> q = new ArrayDeque<>();
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(grid[i][j] == 1) {
                    q.offer(Pair.of(i, j));
                }
            }
        }

        while(!q.isEmpty()) {
            Pair p = q.poll();
            int side = 0;
            for(int[] dir: dirs) {
                int x = p.x + dir[0];
                int y = p.y + dir[1];
                if(x < 0 || y < 0 || x >= m || y >= n) continue;
                if(grid[x][y] == 1) {
                    side++;
                }
            }
            res += (4-side);
        }

        return res;
    }
}
