package com.example.neo4jdemo.movie.repository;

import com.example.neo4jdemo.movie.model.Lineage;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineageRepository extends Neo4jRepository<Lineage, Long> {
}
