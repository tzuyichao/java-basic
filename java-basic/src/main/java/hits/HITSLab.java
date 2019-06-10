package hits;

import java.util.*;

public class HITSLab {
    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int k = 10;

        graph.put(1, Arrays.asList(2, 5));
        graph.put(2, Arrays.asList(4));
        graph.put(3, Arrays.asList(2, 4));
        graph.put(4, new ArrayList<>());
        graph.put(5, Arrays.asList(4));

        List<Double> auth = Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        List<Double> hub = Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);

        graph.keySet().forEach(idx -> {
            auth.set(idx, 1.0);
            hub.set(idx, 1.0);
        });

        for(int step=0; step<k; step++) {
            double norm = 0.0;
            for(Integer idx: graph.keySet()) {
                auth.set(idx, 0.0);

                for(Integer g: graph.keySet()) {
                    if(graph.get(g).contains(idx)) {
                        auth.set(idx, auth.get(idx) + hub.get(g));
                    }
                }

                norm += Math.sqrt(auth.get(idx));
            }
            norm = Math.sqrt(norm);
            for(Integer idx: graph.keySet()) {
                hub.set(idx, 0.0);

                for(Integer outgoing: graph.get(idx)) {
                    hub.set(idx, hub.get(idx) + auth.get(outgoing));
                }

                norm += Math.sqrt(hub.get(idx));
            }
            norm = Math.sqrt(norm);
            for(Integer idx: graph.keySet()) {
                hub.set(idx, hub.get(idx)/norm);
            }
        }

        for(Integer idx: graph.keySet()) {
            System.out.println("Hub (" + idx + "):" + hub.get(idx));
            System.out.println("Auth (" + idx + "):" + auth.get(idx));
        }
    }
}
