package com.example.neo4jdemo.movie.repository;

import com.example.neo4jdemo.movie.model.Domain;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface DomainRepository extends Neo4jRepository<Domain, Long> {

    @Query("Match (root:Domain) where not exists ((root)-[:GLOSSARY_HIERARCHY]->()) return root")
    Collection<Domain> rootDomains();
}
