package brute;

import java.util.ArrayList;
import java.util.List;

/**
 * 547. Number of Provinces
 * https://leetcode.com/problems/number-of-provinces/
 *
 * Runtime: 5 ms, faster than 28.33% of Java online submissions for Number of Provinces.
 * Memory Usage: 53.7 MB, less than 48.68% of Java online submissions for Number of Provinces.
 */
public class NumberOfProvinces {
    List<Integer> parent = new ArrayList<>();
    List<Integer> rank = new ArrayList<>();
    int count = 0;

    int find(int x) {
        while(x != parent.get(x)) {
            parent.set(x, parent.get(parent.get(x)));
            x = parent.get(x);
        }
        return x;
    }

    void union(int x, int y) {
        var rootX = find(x);
        var rootY = find(y);
        if(rootX != rootY) {
            if(rank.get(rootX) > rank.get(rootY)) {
                parent.set(rootY, rootX);
                rank.set(rootX, rank.get(rootX) + 1);
            } else {
                parent.set(rootX, rootY);
                rank.set(rootY, rank.get(rootY) + 1);
            }
            count--;
        }
    }

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;

        for(int i=0; i<n; i++) {
            parent.add(i);
            rank.add(0);
        }
        count = n;

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(i != j && isConnected[i][j] == 1) {
                    union(i, j);
                }
            }
        }

        return count;
    }
}
