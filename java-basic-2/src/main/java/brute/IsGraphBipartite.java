package brute;

/**
 *
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Is Graph Bipartite?.
 * Memory Usage: 54.2 MB, less than 18.08% of Java online submissions for Is Graph Bipartite?.
 *
 * https://zxi.mytechroad.com/blog/graph/leetcode-785-is-graph-bipartite/
 */
public class IsGraphBipartite {
    private boolean coloring(int[][] graph, int[] colors, int color, int node) {
        if(colors[node] != 0) {
            return colors[node] == color;
        }
        colors[node] = color;
        for(int next : graph[node]) {
            if(!coloring(graph, colors, -color, next))
                return false;
        }
        return true;
    }

    public boolean isBipartite(int[][] graph) {
        var n = graph.length;
        int[] colors = new int[n];
        for(int i=0; i<n; i++) {
            if(colors[i] == 0 && !coloring(graph, colors, 1, i))
                return false;
        }
        return true;
    }
}
