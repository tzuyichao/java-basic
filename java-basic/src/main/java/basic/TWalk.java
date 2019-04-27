package basic;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TWalk {
    public static final String NO_ROUTE = "-1";
    private TGraph<String> g;

    public TWalk(TGraph<String> g) {
        this.g = g;
    }

    public void bsf(String start) {
        Queue<String> queue = new PriorityQueue<>();
        Map<String, Boolean> visited = new ConcurrentHashMap<>();
        Map<String, String> pred = new ConcurrentHashMap<>();
        for(String node : g.getNodes()) {
            visited.put(node, Boolean.FALSE);
            pred.put(node, NO_ROUTE);
        }
        visited.put(start, Boolean.TRUE);
        queue.add(start);
        while(!queue.isEmpty()) {
            String v = queue.poll();
            for(String w : g.getLink(v)) {
                if(visited.get(w) == Boolean.FALSE) {
                    visited.put(w, Boolean.TRUE);
                    pred.put(w, v);
                    queue.add(w);
                }
            }
        }
        for(String key: pred.keySet()) {
            System.out.println(key + ": " + pred.get(key));
        }
    }

//    public List<Integer> bsf(int start, int end) {
//        Queue<Integer> queue = new PriorityQueue<>();
//        boolean[] visited = new boolean[g.getV()];
//        int[] pred = new int[g.getV()];
//        for(int i=0; i<visited.length; i++) {
//            visited[i] = false;
//            pred[i] = -1;
//        }
//        visited[start] = true;
//        queue.add(start);
//        while(!queue.isEmpty()) {
//            int v = queue.poll();
//            for(int w : g.getLink(v)) {
//                if(visited[w] == false) {
//                    visited[w] = true;
//                    pred[w] = v;
//                    queue.add(w);
//                }
//            }
//        }
//        for(int i=0; i<pred.length; i++) {
//            System.out.println(i + ": " + pred[i]);
//        }
//        if(pred[end] != -1) {
//            List<Integer> solution = new ArrayList<>();
//            solution.add(end);
//            int backtrack = pred[end];
//            while(pred[backtrack] != -1) {
//                solution.add(backtrack);
//                backtrack = pred[backtrack];
//            }
//            solution.add(backtrack);
//            Collections.reverse(solution);
//            return solution;
//        } else {
//            return Collections.EMPTY_LIST;
//        }
//    }
}
