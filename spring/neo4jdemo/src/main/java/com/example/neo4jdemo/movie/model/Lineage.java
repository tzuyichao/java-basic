package com.example.neo4jdemo.movie.model;

import lombok.Data;
import org.neo4j.ogm.annotation.*;

@Data
@RelationshipEntity(type="LINEAGE")
public class Lineage {
    @Id
    @GeneratedValue
    private Long id;
    @StartNode
    private ColumnUnit source;
    @EndNode
    private ColumnUnit target;
}
