package com.example.neo4jdemo.movie.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@ToString(exclude = {"parent"})
@Data
@NodeEntity
public class Document {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @EqualsAndHashCode.Exclude
    @Relationship(type="CONTAINS", direction = Relationship.INCOMING)
    private Folder parent;
}
