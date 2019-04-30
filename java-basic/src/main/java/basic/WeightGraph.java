package basic;

import java.util.ArrayList;
import java.util.List;

public class WeightGraph {
    // size of vertices
    private int v;
    private List<Node> adj[];

    public WeightGraph(int v) {
        this.v = v;
        adj = new List[v];
        for(int i = 0; i < v; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(int v, Node w) {
        if(!adj[v].contains(w)) {
            adj[v].add(w);
        }
    }

    public int getV() {
        return v;
    }

    public List<Node> getLink(int v) {
        return adj[v];
    }

    public void print() {
        for(int i=0; i<v; i++) {
            System.out.print(i + ":");
            adj[i].forEach(n -> System.out.print(n + " "));
            System.out.println();
        }
    }
}
