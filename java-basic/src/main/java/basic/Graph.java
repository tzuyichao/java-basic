package basic;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    // size of vertices
    private int v;
    private List<Integer> adj[];

    public Graph(int v) {
        this.v = v;
        adj = new List[v];
        for(int i = 0; i < v; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(Integer v, Integer w) {
        if(!adj[v].contains(w)) {
            adj[v].add(w);
        }
    }

    public void print() {
        for(int i=0; i<v; i++) {
            System.out.print(i + ":");
            adj[i].forEach(n -> System.out.print(n + " "));
            System.out.println();
        }
    }
}
