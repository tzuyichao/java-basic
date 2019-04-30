package basic;

import org.junit.Test;

import java.util.List;

public class GraphTest {
    @Test
    public void test_bsf() {
        String[] mapper = new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        Graph graph = new Graph(mapper.length);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 4);
        graph.addEdge(1, 5);
        graph.addEdge(2, 6);
        graph.addEdge(2, 7);
        graph.addEdge(2, 8);
        graph.addEdge(3, 9);

        Walk walk = new Walk(graph);
        List<Integer> solution = walk.bfs(0, 9);
        System.out.println("Solution from A to J:");
        System.out.println(solution);
        System.out.println("Translate: ");
        solution.stream()
                .map(i -> mapper[i])
                .forEach(node -> System.out.print(node + " "));
    }
}
