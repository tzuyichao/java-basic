package com.example.neo4jdemo.movie.repository;

import com.example.neo4jdemo.movie.model.Document;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends Neo4jRepository<Document, Long> {
}
