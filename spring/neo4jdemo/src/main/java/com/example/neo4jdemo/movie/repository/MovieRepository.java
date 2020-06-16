package com.example.neo4jdemo.movie.repository;

import com.example.neo4jdemo.movie.model.Movie;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends Neo4jRepository<Movie, Long> {
}
