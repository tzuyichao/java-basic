package com.example.neo4jdemo.movie.model;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Properties;

import java.util.Map;

@Data
@NodeEntity
public class CatalogUnit {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String desc;
    private ResourceStatus status = ResourceStatus.PUBLISHED;
    private Long version;
    /**
     * application server epoch
     */
    private Long createdTime;
    /**
     * application server epoch
     */
    private Long updatedTime;
    private String createdBy;
    private String updatedBy;
    @Properties(prefix = "property", allowCast = true)
    private Map<String, Object> properties;
}
