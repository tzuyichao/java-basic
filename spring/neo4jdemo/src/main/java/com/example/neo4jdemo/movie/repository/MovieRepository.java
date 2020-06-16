package com.example.neo4jdemo.movie.repository;

import com.example.neo4jdemo.movie.model.Movie;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public interface MovieRepository extends Neo4jRepository<Movie, Long> {
    Movie findByTitle(@Param("title") String title);

    @Query("match (m:Movie) where m.title =~ ('(?i).*'+$title+'.*') return m")
    Collection<Movie> findByTitleContaining(@Param("title") String title);

    @Query("match (m:Movie)<-[:ACTED_IN]-(a:Person) return m.title as movie, collect(a.name) as cast limit $limit")
    List<Map<String, Object>> graph(@Param("limit") int limit);
}
