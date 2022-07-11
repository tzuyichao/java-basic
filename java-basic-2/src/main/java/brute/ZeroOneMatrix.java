package brute;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 542. 01 Matrix
 * https://leetcode.com/problems/01-matrix/
 *
 */
public class ZeroOneMatrix {
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
    }
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] dirs = new int[][] {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

        Queue<Pair> q = new ArrayDeque<>();
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(mat[i][j] == 0) {
                    q.offer(Pair.of(i, j));
                } else {
                    mat[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        while(!q.isEmpty()) {
            Pair pair = q.poll();
            for(int[] dir: dirs) {
                int x = pair.x + dir[0];
                int y = pair.y + dir[1];
                if (x < 0 || x >= m || y < 0 || y >= n || mat[x][y] <= mat[pair.x][pair.y] + 1) continue;
                mat[x][y] = mat[pair.x][pair.y] + 1;
                q.offer(Pair.of(x, y));
            }
        }
        return mat;
    }
}
