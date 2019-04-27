package basic;

import java.util.*;

public class Walk {
    private Graph g;

    public Walk(Graph g) {
        this.g = g;
    }

    public void bsf(int start) {
        Queue<Integer> queue = new PriorityQueue<>();
        boolean[] visited = new boolean[g.getV()];
        int[] pred = new int[g.getV()];
        for(int i=0; i<visited.length; i++) {
            visited[i] = false;
            pred[i] = -1;
        }
        visited[start] = true;
        queue.add(start);
        while(!queue.isEmpty()) {
            int v = queue.poll();
            for(int w : g.getLink(v)) {
                if(visited[w] == false) {
                    visited[w] = true;
                    pred[w] = v;
                    queue.add(w);
                }
            }
        }
        for(int i=0; i<pred.length; i++) {
            System.out.println(i + ": " + pred[i]);
        }
    }

    public List<Integer> bsf(int start, int end) {
        Queue<Integer> queue = new PriorityQueue<>();
        boolean[] visited = new boolean[g.getV()];
        int[] pred = new int[g.getV()];
        for(int i=0; i<visited.length; i++) {
            visited[i] = false;
            pred[i] = -1;
        }
        visited[start] = true;
        queue.add(start);
        while(!queue.isEmpty()) {
            int v = queue.poll();
            for(int w : g.getLink(v)) {
                if(visited[w] == false) {
                    visited[w] = true;
                    pred[w] = v;
                    queue.add(w);
                }
            }
        }
        for(int i=0; i<pred.length; i++) {
            System.out.println(i + ": " + pred[i]);
        }
        if(pred[end] != -1) {
            List<Integer> solution = new ArrayList<>();
            solution.add(end);
            int backtrack = pred[end];
            while(pred[backtrack] != -1) {
                solution.add(backtrack);
                backtrack = pred[backtrack];
            }
            solution.add(backtrack);
            Collections.reverse(solution);
            return solution;
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(7);
        g.addEdge(0, 1);
        g.addEdge(0, 5);

        g.addEdge(1, 0);
        g.addEdge(1, 2);
        g.addEdge(1, 4);
        g.addEdge(1, 6);

        g.addEdge(2, 1);
        g.addEdge(2, 3);
        g.addEdge(2, 6);

        g.addEdge(3, 2);
        g.addEdge(3, 4);
        g.addEdge(3, 6);

        g.addEdge(4, 1);
        g.addEdge(4, 3);
        g.addEdge(4, 5);
        g.addEdge(4, 6);

        g.addEdge(5, 0);
        g.addEdge(5, 4);

        g.addEdge(6, 1);
        g.addEdge(6, 2);
        g.addEdge(6, 3);
        g.addEdge(6, 4);

        g.print();

        System.out.println("bsf:");
        Walk walk = new Walk(g);
        walk.bsf(1);
    }
}
