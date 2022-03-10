package brute;

import java.util.ArrayList;
import java.util.List;

/**
 * 200. Number of Islands
 * https://leetcode.com/problems/number-of-islands/
 */
public class NumberOfIslands {
    class Node {
        int m;
        int n;
        List<Node> children = new ArrayList<>();

        Node(int m, int n) {
            this.m = m;
            this.n = n;
        }

        boolean contains(int m, int n) {
            if(this.m == m && this.n == n) {
                return true;
            }
            return children.stream().anyMatch(node -> node.contains(m, n));
        }
    }

    public int numIslands(char[][] grid) {
        return 0;
    }
}
