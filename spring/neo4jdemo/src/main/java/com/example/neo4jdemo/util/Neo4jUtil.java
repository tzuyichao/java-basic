package com.example.neo4jdemo.util;

import org.neo4j.driver.types.Entity;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Path;
import org.neo4j.driver.types.Relationship;

public final class Neo4jUtil {
    public static String dumpProperty(Entity entity) {
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        for(String key : entity.keys()) {
            sb.append(key).append(":").append(entity.get(key)).append(",");
        }
        sb.append("}");
        return sb.toString();
    }

    public static String dumpNode(Node node) {
        return String.format("(Node(%s) %s)", String.join(", ", node.labels()), dumpProperty(node));
    }

    public static String dumpRelationship(Relationship relationship) {
        return relationship.type();
    }

    public static void printSegment(Path.Segment segment) {
        System.out.println(dumpNode(segment.start()) + " - " + dumpRelationship(segment.relationship()) + " - " + dumpNode(segment.end()));
    }
}
