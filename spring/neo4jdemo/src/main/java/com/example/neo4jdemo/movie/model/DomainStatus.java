package com.example.neo4jdemo.movie.model;

public enum DomainStatus {
    OPEN("OPEN"),
    DELETED("DELETED"),
    DRAFT("DRAFT");

    private String val;
    DomainStatus(String val) {
        this.val = val;
    }
}
