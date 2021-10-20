package com.example.neo4jdemo.movie.repository;

import com.example.neo4jdemo.movie.model.Domain;
import com.example.neo4jdemo.movie.model.DomainStatus;
import org.neo4j.ogm.model.Result;
import org.springframework.data.neo4j.annotation.Depth;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface DomainRepository extends Neo4jRepository<Domain, Long> {

    Long countByNameAndStatusNotLike(String name, DomainStatus status);

    @Query("Match (d:Domain {name: $name}) where d.status in $status return count(d)")
    Long countByNameAndStatusIn(String name, Iterable<DomainStatus> status);

    @Query("Match (d:Domain {name: $name}) where d.status <> 'DELETED' return d")
    Collection<Domain> findByName(@Param("name") String name);

    List<Domain> findByNameAndStatus(String name, DomainStatus status, @Depth int depth);

    @Query("Match (s:Domain)-[:GLOSSARY_HIERARCHY]->(r:Domain) where id(s)=$id and r.status <> 'DELETED' return r limit 1")
    Domain parentDomain(@Param("id") Long id);

    @Query("Match (root:Domain) where root.status <> 'DELETED' and not exists ((root)-[:GLOSSARY_HIERARCHY]->()) return root")
    Collection<Domain> rootDomains();

    @Query("Match (p:Domain)<-[:GLOSSARY_HIERARCHY]-(c:Domain) where p.name = $parentName and c.name = $name and c.status <> 'DELETED' return count((p:Domain)<-[:GLOSSARY_HIERARCHY]-(c:Domain)) = 0")
    boolean checkDomainName(@Param("parentName") String parentName, @Param("name") String name);

    @Query("Match (p:Domain)<-[:GLOSSARY_HIERARCHY]-(c:Domain) where p.id = $parentId and c.name = $name and c.status <> 'DELETED' return count((p:Domain)<-[:GLOSSARY_HIERARCHY]-(c:Domain)) = 0")
    boolean checkDomainName(@Param("parentId") Long parentId, @Param("name") String name);

    @Query("match (d:Domain) where id(d) = $id and d.status <> 'DELETED' return count(d)=1")
    boolean checkDomainExist(@Param("id") Long id);

    @Query("match p=((s:Domain {name: $sourceName})-[:GLOSSARY_HIERARCHY*]->(d:Domain {name: $destName})) where d.status <> 'DELETED' and s.status <> 'DELETED' return p")
    Result paths(@Param("sourceName") String sourceName, @Param("destName") String destName);

    @Query("match (d:Domain {name: $name})<-[r:GLOSSARY_HIERARCHY*]-(n:Domain) where d.status <> 'DELETED' and n.status <> 'DELETED' set d.status = $status, n.status = $status")
    Result updateDomainStatusCascade(@Param("name") String name, @Param("status") DomainStatus status);

    @Query("Match (d:Domain)-[r:GLOSSARY_HIERARCHY]-(c:Domain) where id(d) = $id delete r")
    Result deleteOtherDomainRelationship(@Param("id") Long id);

    @Query("Match (d:Domain) where d.name=$domain.name set d.desc = $domain.desc return d")
    Optional<Domain> searchTest(@Param("domain") Domain domain);
}
