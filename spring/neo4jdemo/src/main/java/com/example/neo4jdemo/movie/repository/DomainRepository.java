package com.example.neo4jdemo.movie.repository;

import com.example.neo4jdemo.movie.model.Domain;
import org.neo4j.ogm.model.Result;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface DomainRepository extends Neo4jRepository<Domain, Long> {
    @Query("Match (d:Domain {name: $name}) where d.status <> 'DELETED' return d")
    Collection<Domain> findByName(@Param("name") String name);

    @Query("Match (root:Domain) where root.status <> 'DELETED' and not exists ((root)-[:GLOSSARY_HIERARCHY]->()) return root")
    Collection<Domain> rootDomains();

    @Query("Match (p:Domain)<-[:GLOSSARY_HIERARCHY]-(c:Domain) where p.name = $parentName and c.name = $name and c.status <> 'DELETED' return count((p:Domain)<-[:GLOSSARY_HIERARCHY]-(c:Domain)) = 0")
    boolean checkDomainName(@Param("parentName") String parentName, @Param("name") String name);

    @Query("Match (p:Domain)<-[:GLOSSARY_HIERARCHY]-(c:Domain) where p.id = $parentId and c.name = $name and c.status <> 'DELETED' return count((p:Domain)<-[:GLOSSARY_HIERARCHY]-(c:Domain)) = 0")
    boolean checkDomainName(@Param("parentId") Long parentId, @Param("name") String name);

    @Query("match (d:Domain) where id(d) = $id and d.status <> 'DELETED' return count(d)=1")
    boolean checkDomainExist(@Param("id") Long id);

    @Query("match p=((:Domain {name: $sourceName})-[:GLOSSARY_HIERARCHY*]->(:Domain {name: $destName})) return p")
    Result paths(@Param("sourceName") String sourceName, @Param("destName") String destName);
}
