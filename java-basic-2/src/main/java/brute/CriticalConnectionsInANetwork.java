package brute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CriticalConnectionsInANetwork {
    private int currentTime = 0;
    private int[] times;
    private int[] lowTimes;
    private boolean[] visitedNodes;
    List<List<Integer>> res = new ArrayList<>();
    List<Integer>[] graph;

    private void buildGraph(int n, List<List<Integer>> connections) {
        graph = new ArrayList[n];
        for(var i=0; i<n; i++) {
            graph[i] = new ArrayList<>();
        }
        for(List<Integer> connection: connections) {
            graph[connection.get(0)].add(connection.get(1));
            graph[connection.get(1)].add(connection.get(0));
        }
    }

    private boolean dfs(int currentNode, int parentNode) {
        visitedNodes[currentNode] = true;
        return false;
    }

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        times = new int[n];
        lowTimes = new int[n];
        visitedNodes = new boolean[n];

        dfs(0, -1);

        return res;
    }
}
