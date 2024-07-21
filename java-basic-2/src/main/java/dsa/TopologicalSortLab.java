package dsa;

import java.util.*;

public class TopologicalSortLab {
    /**
     * make in-degree
     * @param graph
     * @return
     * @param <T>
     */
    public static <T> Map<T, Integer> findInDegree(Map<T, List<T>> graph) {
        Map<T, Integer> inDegree = new HashMap<T, Integer>();

        graph.keySet().forEach(node -> {
            inDegree.put(node, 0);
        });
        graph.entrySet().forEach(entry -> {
            for(T neighbor : entry.getValue()) {
                inDegree.put(neighbor, inDegree.get(neighbor) + 1);
            }
        });

        return inDegree;
    }

    public static <T> List<T> topologicalSort(Map<T, List<T>> graph) {
        List<T> res = new ArrayList<T>();

        Queue<T> q = new ArrayDeque<>();
        Map<T, Integer> inDegree = findInDegree(graph);

        inDegree.entrySet().forEach(entry -> {
            if(entry.getValue() == 0) {
                q.add(entry.getKey());
            }
        });

        while(!q.isEmpty()) {
            T node = q.poll();
            res.add(node);
            for(T neighbor : graph.get(node)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if(inDegree.get(neighbor) == 0) {
                    q.add(neighbor);
                }
            }
        }

        return (graph.size() == res.size())?res:null;
    }

    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(4, List.of(2));
        graph.put(2, List.of(1));
        graph.put(3, List.of(1));
        graph.put(1, Collections.emptyList());

        System.out.println(findInDegree(graph));
        System.out.println(topologicalSort(graph));
    }
}
