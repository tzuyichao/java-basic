package basic;

import org.apache.tinkerpop.gremlin.process.computer.traversal.step.map.ShortestPath;
import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.process.traversal.step.map.GraphStep;
import org.apache.tinkerpop.gremlin.process.traversal.util.Metrics;
import org.apache.tinkerpop.gremlin.process.traversal.util.TraversalMetrics;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.T;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.VertexProperty;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.apache.tinkerpop.gremlin.util.iterator.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.NoSuchElementException;

import static org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__.*;

public class HelloWorld {
    private static final Logger logger = LoggerFactory.getLogger(HelloWorld.class);

    public static void main(String[] args) {
        GraphTraversalSource graphTraversalSource = Util.modern();

        // get vertex via traversal machine
        final Vertex fromNode = graphTraversalSource.V().has("name", "vadas").next();
        final Vertex toNode = graphTraversalSource.V().has("name", "peter").next();

        logger.info("fromNode: " + fromNode.value("name").toString());
        logger.info("toNode: " + toNode.value("name").toString());
        // 取一條無方向性簡單路徑
        Path path = graphTraversalSource.V(fromNode)
                .repeat(both().simplePath()).until(is(toNode)).path()
                .limit(1).next();
        logger.info("Path detail:");
        path.forEach(v -> logger.info(((Vertex) v).value("name")));

        try {
            Path pathNotExist = graphTraversalSource.V(fromNode)
                    .repeat(out().simplePath()).until(is(toNode)).path()
                    .limit(1).next();
            logger.info(path.toString());
        } catch(NoSuchElementException e) {
            // 只有這個判斷方法??
            logger.info("Path not found");
        }

        logger.info("Shortest Path Test");

        final GraphTraversalSource shortestGraphTraversalSource = graphTraversalSource.withComputer();
        Path shortestPath = shortestGraphTraversalSource.V().has("Person", "name", "josh").shortestPath()
                .with(ShortestPath.target, has("Person", "name", "vadas"))
                .with(ShortestPath.includeEdges, true).next();
        shortestPath.forEach(v -> {
            if(v instanceof Vertex) {
                Vertex vertex = (Vertex) v;
                logger.info(vertex.label() + " " + vertex.id());
                // withComputer()之後就只剩vertex, edge, label, id (maybe weighted properties)
                Vertex v1 = graphTraversalSource.V().hasId(vertex.id()).next();
                logger.info("name: {}", v1.value("name").toString());
            }
            if(v instanceof Edge) {
                Edge edge = (Edge) v;
                logger.info(edge.label());
            }
        });

        // toList example
        System.out.println("Shortest Path toList()");
        Object[] result = shortestGraphTraversalSource.V().has("Person", "name", "josh").shortestPath()
                .with(ShortestPath.target, has("Person", "name", "vadas"))
                .with(ShortestPath.includeEdges, true)
                .toList()
                .toArray();
        List selectPropertyResult = graphTraversalSource.inject(result).map(unfold().values("name", "weight").fold()).next();
        selectPropertyResult.stream().forEach(path1 -> logger.info(path1.toString()));

        logger.info("Vertex Count: {}", IteratorUtils.count(graphTraversalSource.getGraph().vertices()));
        logger.info("Edge Count: {}", IteratorUtils.count(graphTraversalSource.getGraph().edges()));

        TraversalMetrics traversalMetrics = graphTraversalSource.V(fromNode)
                .repeat(both().simplePath()).until(is(toNode))
                .path()
                .profile().next();
        logger.info(traversalMetrics.toString());
        traversalMetrics.getMetrics().stream().forEach(metrics -> {
            logger.info(metrics.toString());
        });
    }
}
