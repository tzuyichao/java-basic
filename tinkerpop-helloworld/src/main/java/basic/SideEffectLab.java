package basic;

import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.process.traversal.step.util.BulkSet;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import static org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__.*;

public class SideEffectLab {
    public static void main(String[] args) {
        GraphTraversalSource graphTraversalSource = Util.modern();
        // 呼叫terminal steps類型的sideEffect才會被觸發，在gremlin console可用推測是因為repl會嘗試取值印到console
        graphTraversalSource.V().hasLabel("Person").sideEffect(System.out::println).iterate();

        System.out.println("SideEffect with simple path");
        final Vertex fromNode = graphTraversalSource.V().has("name", "vadas").next();
        final Vertex toNode = graphTraversalSource.V().has("name", "peter").next();

        Path path =graphTraversalSource.V(fromNode)
                .repeat(both().simplePath().sideEffect(tv -> {
                    System.out.println(tv.get().value("name").toString());
                }))
                .until(is(toNode))
                .path()
                .limit(1)
                .next();
        System.out.println("Print Path Details:");
        path.forEach(v -> {
            System.out.println(((Vertex) v).value("name").toString());
        });
    }
}
