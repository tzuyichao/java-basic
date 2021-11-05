package com.example.neo4jdemo.movie.service;

import java.util.Map;

public interface LinkageService {
    void export(String cypher, Map<String, ?> parameters, String filename);
}
