package com.example.neo4jdemo.movie.repository;

import com.example.neo4jdemo.movie.model.Domain;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface DomainRepository extends Neo4jRepository<Domain, Long> {

    @Query("Match (root:Domain) where not exists ((root)-[:GLOSSARY_HIERARCHY]->()) return root")
    Collection<Domain> rootDomains();

    @Query("Match (p:Domain)<-[:GLOSSARY_HIERARCHY]-(c:Domain) where p.name = $parentName and c.name = $name return count((p:Domain)<-[:GLOSSARY_HIERARCHY]-(c:Domain)) = 0")
    boolean checkDomainName(@Param("parentName") String parentName, @Param("name") String name);

}
