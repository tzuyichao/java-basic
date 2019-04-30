package basic;

import org.junit.Test;

import java.math.BigDecimal;

public class WeightGraphTest {

    @Test
    public void test_print_graph() {
        WeightGraph graph = new WeightGraph(10);

        graph.addEdge(0, new Node(1, BigDecimal.valueOf(2.7)));
        graph.addEdge(0, new Node(2, BigDecimal.valueOf(1.7)));
        graph.addEdge(0, new Node(3, BigDecimal.valueOf(3.7)));
        graph.addEdge(1, new Node(4, BigDecimal.valueOf(2.0)));
        graph.addEdge(1, new Node(5, BigDecimal.valueOf(0.8)));
        graph.addEdge(2, new Node(6, BigDecimal.valueOf(1.0)));
        graph.addEdge(2, new Node(7, BigDecimal.valueOf(0.9)));
        graph.addEdge(2, new Node(8, BigDecimal.valueOf(5.7)));
        graph.addEdge(3, new Node(9, BigDecimal.valueOf(2.3)));

        graph.print();
    }
}
