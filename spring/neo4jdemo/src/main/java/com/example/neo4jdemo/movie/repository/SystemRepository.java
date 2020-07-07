package com.example.neo4jdemo.movie.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemRepository extends Neo4jRepository<System, Long> {
}
