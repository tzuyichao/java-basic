package basic;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.T;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;

final class Util {
    static GraphTraversalSource modern() {
        // create in-memory graph
        TinkerGraph graph = TinkerGraph.open();
        // get Gremlin traversal machine on graph
        GraphTraversalSource graphTraversalSource = graph.traversal();

        // add vertex method 1
        final Vertex marko = graphTraversalSource.addV("Person").next();
        marko.property("name", "marko");
        marko.property("age", 29);
        final Vertex vadas = graphTraversalSource.addV("Person").next();
        vadas.property("name", "vadas");
        vadas.property("age", 27);
        final Vertex lop = graphTraversalSource.addV("Software").next();
        lop.property("name", "lop");
        lop.property("lang", "java");
        final Vertex josh = graphTraversalSource.addV("Person").next();
        josh.property("name", "josh");
        josh.property("age", 32);
        // add vertex method 2
        final Vertex ripple = graph.addVertex(T.label, "Software", "name", "ripple", "lang", "java");
        final Vertex peter = graph.addVertex(T.label, "People", "name", "peter", "age", 35);

        // add edges
        marko.addEdge("knows", vadas, "weight", 0.5f);
        marko.addEdge("knows", josh, "weight", 1.0f);
        marko.addEdge("created", lop, "weight", 0.4f);
        josh.addEdge("created", ripple, "weight", 1.0f);
        josh.addEdge("created", lop, "weight", 0.4f);
        peter.addEdge("created", lop, "weight", 0.2f);

        return graphTraversalSource;
    }
}
