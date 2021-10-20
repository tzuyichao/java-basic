package com.example.neo4jdemo.movie.repository;

import com.example.neo4jdemo.movie.model.Folder;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository extends Neo4jRepository<Folder, Long> {
}
