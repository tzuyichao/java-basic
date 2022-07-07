package brute;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * 695. Max Area of Island
 * https://leetcode.com/problems/max-area-of-island/
 *
 */
public class MaxAreaOfIsland {
    static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return x + ":" + y;
        }
    }
    public int sizeOf(int[][] grid, int x, int y, Set<String> v) {
        Set<String> visited = new HashSet();
        Queue<Pair> q = new ArrayDeque<>();
        q.offer(new Pair(x, y));
        while(!q.isEmpty()) {
            Pair node = q.poll();
            if(!visited.contains(node.toString())) {
                visited.add(node.toString());
                v.add(node.toString());

                if(node.x - 1 >= 0 && grid[node.x-1][node.y] == 1) {
                    q.offer(new Pair(node.x-1, node.y));
                }
                if(node.x + 1 < grid.length && grid[node.x+1][node.y] == 1) {
                    q.offer(new Pair(node.x+1, node.y));
                }
                if(node.y - 1 >= 0 && grid[node.x][node.y-1] == 1) {
                    q.offer(new Pair(node.x, node.y-1));
                }
                if(node.y + 1 < grid[0].length && grid[node.x][node.y+1] == 1) {
                    q.offer(new Pair(node.x, node.y+1));
                }
            }
        }
        //System.out.println(x+ ":" + y + "=" + visited.size());
        return visited.size();
    }

    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;

        int m = grid.length;
        int n = grid[0].length;
        Set<String> v = new HashSet();

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(grid[i][j] == 1 && !v.contains(i + ":" + j)) {
                    res = Math.max(res, sizeOf(grid, i, j, v));
                }
            }
        }

        return res;
    }
}
