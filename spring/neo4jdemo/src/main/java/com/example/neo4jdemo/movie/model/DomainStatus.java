package com.example.neo4jdemo.movie.model;

public enum DomainStatus {
    OPEN("OPEN"),
    DELETED("DELETED"),
    PUBLISHED("PUBLISHED"),
    DRAFT("DRAFT");

    private String val;
    DomainStatus(String val) {
        this.val = val;
    }
}
