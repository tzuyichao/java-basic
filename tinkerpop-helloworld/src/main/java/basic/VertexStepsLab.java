package basic;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class VertexStepsLab {
    private static final Logger logger = LoggerFactory.getLogger(VertexStepsLab.class);
    public static void main(String[] args) {
        GraphTraversalSource graphTraversalSource = Util.modern();
        List<Edge> edges = graphTraversalSource.V().has("name", "lop").inE("created").toList();
        edges.forEach(edge -> {
            logger.info(edge.toString());
            logger.info("edge label: {} weight: {}", edge.label(), edge.value("weight").toString());
            logger.info("in vertex name: {}", edge.inVertex().value("name").toString());
            logger.info("out vertex name: {}", edge.outVertex().value("name").toString());
        });
    }
}
