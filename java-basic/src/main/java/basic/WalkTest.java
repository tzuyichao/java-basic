package basic;

import java.util.List;

public class WalkTest {
    public static void main(String[] args) {
        Graph graph = new Graph(10);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 4);
        graph.addEdge(1, 5);
        graph.addEdge(2, 6);
        graph.addEdge(2, 7);
        graph.addEdge(2, 8);
        graph.addEdge(3, 9);

        graph.print();

        Walk walk = new Walk(graph);
        System.out.println("bfs from 0:");
        walk.bfs(0);
        System.out.println("dfs from 0:");
        walk.dfs(0);

        System.out.println("Prepare from 0 to 9:");
        List<Integer> solution = walk.bfs(0, 9);
        System.out.println("Solution from 0 to 9:");
        System.out.println(solution);
    }
}
