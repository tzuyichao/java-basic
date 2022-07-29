package brute;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 1971. Find if Path Exists in Graph
 * https://leetcode.com/problems/find-if-path-exists-in-graph/
 *
 */
public class FindIfPathExistsInGraph {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        if(n==1 || (source == destination)) {
            return true;
        }
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> visited = new ArrayList<>();
        queue.offer(source);
        visited.add(source);
        while(!queue.isEmpty()) {
            Integer curr = queue.poll();
            for(int[] edge: edges) {
                if(edge[0] == curr) {
                    if(edge[1] == destination) {
                        return true;
                    } else {
                        if(!visited.contains(edge[1])) {
                            queue.offer(edge[1]);
                            visited.add(edge[1]);
                        }
                    }
                }
                if(edge[1] == curr) {
                    if(edge[0] == destination) {
                        return true;
                    } else {
                        if(!visited.contains(edge[0])) {
                            queue.offer(edge[0]);
                            visited.add(edge[0]);
                        }
                    }
                }
            }
        }
        return false;
    }
}
