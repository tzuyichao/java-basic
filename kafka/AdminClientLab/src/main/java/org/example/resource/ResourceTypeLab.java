package org.example.resource;

import org.apache.kafka.common.resource.ResourceType;

public class ResourceTypeLab {
    public static void main(String[] args) {
        System.out.println(ResourceType.fromString("topic"));
        System.out.println(ResourceType.fromString("group"));
        System.out.println(ResourceType.fromString("group1"));
    }
}
