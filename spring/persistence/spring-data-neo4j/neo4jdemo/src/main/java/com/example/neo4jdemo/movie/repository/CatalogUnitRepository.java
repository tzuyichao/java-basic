package com.example.neo4jdemo.movie.repository;

import com.example.neo4jdemo.movie.model.CatalogUnit;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogUnitRepository extends Neo4jRepository<CatalogUnit, Long> {
    @Query("MATCH (n:CatalogUnit) WHERE id(n) = $id return labels(n)")
    String getNodeLabelsById(Long id);
}
