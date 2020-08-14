package com.example.neo4jdemo.movie.repository;

import com.example.neo4jdemo.movie.model.ColumnUnit;
import com.example.neo4jdemo.movie.model.Lineage;
import org.neo4j.ogm.model.Result;
import org.springframework.data.neo4j.annotation.Depth;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface LineageRepository extends Neo4jRepository<Lineage, Long> {
    @Query("MATCH (s:ColumnUnit)-[r:LINEAGE_LINK]->(t:ColumnUnit) WHERE id(s)=$sourceId return s, r, t")
    Lineage[] findBySource(Long sourceId);
}
