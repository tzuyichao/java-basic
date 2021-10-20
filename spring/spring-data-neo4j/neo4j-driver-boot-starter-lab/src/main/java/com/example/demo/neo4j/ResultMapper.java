package com.example.demo.neo4j;

import com.example.demo.controller.model.MovieValueObject;
import com.example.demo.neo4j.annotation.NodeLabel;
import org.neo4j.driver.types.Node;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashMap;
import java.util.Map;

public final class ResultMapper {
    private Map<String, Class> nodeLabelModelMap = new HashMap<>();

    public <T> void registryClass(Class<T> valueType) {
        NodeLabel nodeLabel = valueType.getAnnotation(NodeLabel.class);
        if(nodeLabel != null) {
            nodeLabelModelMap.put(nodeLabel.value(), valueType);
        }
    }

    public <T> T readValue(Node node, Class<T> valueType) {
//        if(null == node) {
//            throw new IllegalArgumentException("input null ...");
//        }
        NodeLabel nodeLabel = valueType.getAnnotation(NodeLabel.class);
        System.out.println(nodeLabel.value());
//        BeanWrapper wrapper = new BeanWrapperImpl(node);
//        for(String key : node.keys()) {
//            System.out.println(key);
//        }
        return null;
    }

    public static void main(String[] args) {
        ResultMapper resultMapper = new ResultMapper();
        resultMapper.readValue(null, MovieValueObject.class);
        resultMapper.readValue(null, ResultMapper.class);
    }
}
