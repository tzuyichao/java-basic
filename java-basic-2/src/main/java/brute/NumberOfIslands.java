package brute;

import java.util.ArrayList;
import java.util.List;

/**
 * 200. Number of Islands
 * https://leetcode.com/problems/number-of-islands/
 *
 * Runtime: 21 ms, faster than 7.48% of Java online submissions for Number of Islands.
 * Memory Usage: 54.6 MB, less than 74.65% of Java online submissions for Number of Islands.
 */
public class NumberOfIslands {
    class UF {
        List<Integer> parent = new ArrayList<>();
        List<Integer> rank = new ArrayList<>();
        int count;

        UF(char[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            for(int i=0; i<m; i++) {
                for(int j=0; j<n; j++) {
                    if(grid[i][j] == '1') {
                        parent.add(i*n+j);
                        count++;
                    } else {
                        parent.add(-1);
                    }
                    rank.add(i*n+j);
                }
            }
        }

        int find(int i) {
            while(i != parent.get(i)) {
                parent.set(i, parent.get(parent.get(i)));
                i = parent.get(i);
            }
            return parent.get(i);
        }
        void union(int x, int y) {
            var rootX = find(x);
            var rootY = find(y);
            if(rootX != rootY) {
                // union by rank
                if(rank.get(rootX) > rank.get(rootY)) {
                    parent.set(rootY, rootX);
                    rank.set(rootX, rank.get(rootX)+1);
                } else {
                    parent.set(rootX, rootY);
                    rank.set(rootY, rank.get(rootY)+1);
                }
                count--;
            }
        }
        int count() {
            return count;
        }
    }

    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        var uf = new UF(grid);

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(grid[i][j] == '1') {
                    grid[i][j] = '0';
                    if(i-1 >= 0 && grid[i-1][j] == '1') {
                        uf.union(i*n+j, (i-1)*n+j);
                    }
                    if(i+1 < m && grid[i+1][j] == '1') {
                        uf.union(i*n+j, (i+1)*n+j);
                    }
                    if(j-1 >= 0 && grid[i][j-1] == '1') {
                        uf.union(i*n+j, i*n+(j-1));
                    }
                    if(j+1 < n && grid[i][j+1] == '1') {
                        uf.union(i*n+j, i*n+(j+1));
                    }
                }
            }
        }

        return uf.count();
    }
}
