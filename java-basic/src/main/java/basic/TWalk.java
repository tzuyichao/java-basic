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

    public List<String> bsf(String start, String target) {
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
        if(pred.get(target) != NO_ROUTE) {
            List<String> solution = new ArrayList<>();
            solution.add(target);
            String backtrack = pred.get(target);
            while(pred.get(backtrack) != NO_ROUTE) {
                solution.add(backtrack);
                backtrack = pred.get(backtrack);
            }
            solution.add(backtrack);
            Collections.reverse(solution);
            return solution;
        } else {
            return Collections.EMPTY_LIST;
        }
    }
}
