package com.example.neo4jdemo.movie.model;

import lombok.Data;
import org.neo4j.ogm.annotation.NodeEntity;

@Data
@NodeEntity
public class ColumnUnit extends CatalogUnit {
}
