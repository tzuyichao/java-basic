package com.example.neo4jdemo.movie.model;

import lombok.Getter;

@Getter
public enum ResourceStatus {
    PUBLISHED("PUBLISHED"),
    PENDING("PENDING"),
    DELETED("DELETED"),
    HISTORY("HISTORY");

    private String val;
    ResourceStatus(String val) {
        this.val = val;
    }
}
