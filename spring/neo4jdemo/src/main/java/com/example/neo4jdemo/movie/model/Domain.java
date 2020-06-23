package com.example.neo4jdemo.movie.model;

import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Collection;

@Data
@Builder
@NodeEntity
public class Domain {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Relationship(type="GLOSSARY_HIERARCHY", direction=Relationship.INCOMING)
    private Collection<Domain> children;
}
