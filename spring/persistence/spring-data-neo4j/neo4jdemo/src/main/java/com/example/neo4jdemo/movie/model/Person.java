package com.example.neo4jdemo.movie.model;

import lombok.Data;
import lombok.ToString;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@ToString(exclude = {"movies"})
@Data
@NodeEntity
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    private Integer born;
    private String name;

    @Relationship(type="ACTED_IN")
    private List<Movie> movies;
}
