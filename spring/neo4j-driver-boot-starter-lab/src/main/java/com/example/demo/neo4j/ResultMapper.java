package com.example.demo.neo4j;

import com.example.demo.controller.model.MovieValueObject;
import com.example.demo.neo4j.annotation.NodeLabel;
import org.neo4j.driver.types.Node;

public final class ResultMapper {
    public <T> T readValue(Node node, Class<T> valueType) {
        NodeLabel nodeLabel = valueType.getAnnotation(NodeLabel.class);
        System.out.println(nodeLabel.value());
        return null;
    }

    public static void main(String[] args) {
        ResultMapper resultMapper = new ResultMapper();
        resultMapper.readValue(null, MovieValueObject.class);
    }
}
