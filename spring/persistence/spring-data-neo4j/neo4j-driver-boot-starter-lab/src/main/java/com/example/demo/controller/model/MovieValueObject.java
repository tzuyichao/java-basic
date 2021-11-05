package com.example.demo.controller.model;

import com.example.demo.neo4j.annotation.NodeLabel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Builder
@Getter
@NodeLabel(value="Movie")
public class MovieValueObject {
    private final Long id;
    private final String title;
    private final String released;
    private final String tagline;
}
