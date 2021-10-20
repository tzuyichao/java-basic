package com.example.neo4jdemo.movie.repository;

import com.example.neo4jdemo.movie.model.ColumnUnit;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColumnUnitRepository extends Neo4jRepository<ColumnUnit, Long> {
}
