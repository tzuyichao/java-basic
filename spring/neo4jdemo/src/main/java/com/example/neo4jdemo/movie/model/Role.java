package com.example.neo4jdemo.movie.model;

import lombok.Data;
import lombok.ToString;
import org.neo4j.ogm.annotation.*;

import java.util.Collection;

@Data
@RelationshipEntity(type="ACTED_IN")
public class Role {
    @Id
    @GeneratedValue
    private Long id;

    private Collection<String> roles;

    @StartNode
    private Person person;

    @EndNode
    private Movie movie;
}
