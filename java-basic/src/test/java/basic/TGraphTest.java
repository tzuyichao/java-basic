package basic;

import org.junit.Test;

import java.util.List;

public class TGraphTest {
    @Test
    public void test_print_graph() {
        TGraph<String> graph = new TGraph<String>();
        graph.addEdge("1", "2");
        graph.addEdge("1", "3");
        graph.addEdge("1", "4");
        graph.addEdge("2", "5");
        graph.addEdge("2", "6");
        graph.addEdge("3", "7");
        graph.addEdge("3", "8");
        graph.addEdge("3", "9");
        graph.addEdge("4", "10");
        graph.addEdge("4", "11");
        graph.addEdge("7", "12");
        graph.addEdge("7", "13");

        graph.print();

        System.out.println("WALK:");
        TWalk walk = new TWalk(graph);
        walk.bfs("1");

        System.out.println("Path:");
        List<String> solution = walk.bfs("1", "13");
        solution.forEach(node -> System.out.print(node + " "));
    }
}
