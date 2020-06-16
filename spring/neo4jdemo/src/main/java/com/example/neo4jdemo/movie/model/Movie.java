package com.example.neo4jdemo.movie.model;


import lombok.Data;
import lombok.ToString;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@ToString(exclude = {"roles"})
@Data
@NodeEntity
public class Movie {
    @Id
    @GeneratedValue
    private Long id;

    private Integer released;
    private String title;
    private String tagline;

    @Relationship(type="ACTED_IN", direction=Relationship.INCOMING)
    private List<Role> roles;
}
