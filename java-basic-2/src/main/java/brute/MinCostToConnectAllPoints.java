package brute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 1584. Min Cost to Connect All Points
 * https://leetcode.com/problems/min-cost-to-connect-all-points/
 *
 * Runtime: 493 ms, faster than 42.61% of Java online submissions for Min Cost to Connect All Points.
 * Memory Usage: 60.3 MB, less than 88.98% of Java online submissions for Min Cost to Connect All Points.
 *
 * https://zxi.mytechroad.com/blog/graph/leetcode-1584-min-cost-to-connect-all-points/
 * https://www.programiz.com/dsa/kruskal-algorithm
 */
public class MinCostToConnectAllPoints {
    static class Edge {
        int cost;
        int x;
        int y;

        public Edge(int cost, int x, int y) {
            this.cost = cost;
            this.x = x;
            this.y = y;
        }

        public int getCost() {
            return cost;
        }

        public String toString() {
            return String.format("x: %d, y: %d, cost: %d", x, y, cost);
        }
    }

    static class Subset {
        int parent;
        int rank;

        public Subset(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }
    }

    int find(Subset[] subsets, int i) {
        if(subsets[i].parent != i) {
            subsets[i].parent = find(subsets, subsets[i].parent);
        }
        return subsets[i].parent;
    }

    void union(Subset[] subsets, int x, int y) {
        int xRoot = find(subsets, x);
        int yRoot = find(subsets, y);

        if(subsets[xRoot].rank < subsets[yRoot].rank) {
            subsets[xRoot].parent = yRoot;
        } else if(subsets[xRoot].rank > subsets[yRoot].rank) {
            subsets[yRoot].parent = xRoot;
        } else {
            subsets[yRoot].parent = xRoot;
            subsets[xRoot].rank++;
        }
    }

    public int distance(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }

    public int minCostConnectPoints(int[][] points) {
        var n = points.length;
        var edges = new ArrayList<Edge>();
        for(var i=0; i<n; i++) {
            for(var j=i+1; j<n; j++) {
                edges.add(new Edge(distance(points[i], points[j]), i, j));
            }
        }
        Collections.sort(edges, Comparator.comparingInt(Edge::getCost));
        //System.out.println(edges);
        var subsets = new Subset[n];
        for(int i=0; i<n; i++) {
            subsets[i] = new Subset(i, 0);
        }
        int i = 0;
        int j = 0;
        int res = 0;
        while(j < n-1) {
            var next_edge = edges.get(i++);
            int x = find(subsets, next_edge.x);
            int y = find(subsets, next_edge.y);
            if(x != y) {
                union(subsets, x, y);
                res += next_edge.cost;
                j++;
            }
        }

        return res;
    }
}
