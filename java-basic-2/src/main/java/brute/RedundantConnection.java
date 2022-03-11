package brute;

import java.util.ArrayList;
import java.util.List;

/**
 * 684. Redundant Connection
 * https://leetcode.com/problems/redundant-connection/
 *
 * Runtime: 5 ms, faster than 24.34% of Java online submissions for Redundant Connection.
 * Memory Usage: 44.1 MB, less than 34.08% of Java online submissions for Redundant Connection.
 */
public class RedundantConnection {
    List<Integer> parent = new ArrayList<>();
    List<Integer> rank = new ArrayList<>();

    int find(int x) {
        while(x != parent.get(x)) {
            parent.set(x, parent.get(parent.get(x)));
            x = parent.get(x);
        }
        return x;
    }

    boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if(rootX == rootY) {
            return false;
        } else {
            if(rank.get(x) > rank.get(y)) {
                parent.set(rootY, rootX);
            } else if(rank.get(y) > rank.get(x)) {
                parent.set(rootX, rootY);
            } else {
                parent.set(rootX, rootY);
                rank.set(y, rank.get(y)+1);
            }
        }
        return true;
    }

    public int[] findRedundantConnection(int[][] edges) {
        int m = edges.length;
        // init each node self parent and rank to zero
        for(int i=0; i<=m; i++) {
            parent.add(i);
            rank.add(0);
        }
        for(int i=0; i<m; i++) {
            if(!union(edges[i][0], edges[i][1])) {
                return new int[] {edges[i][0], edges[i][1]};
            }
        }

        return new int[]{};
    }
}
