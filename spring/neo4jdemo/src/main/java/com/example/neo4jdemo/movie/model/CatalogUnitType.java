package com.example.neo4jdemo.movie.model;

public enum CatalogUnitType {
    System("System"),
    DB("DB");

    String val;
    CatalogUnitType(String val) {
        this.val = val;
    }

    public String val() {
        return val;
    };
}
