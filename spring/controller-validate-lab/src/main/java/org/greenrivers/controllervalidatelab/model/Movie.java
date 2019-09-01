package org.greenrivers.controllervalidatelab.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter @Setter @ToString
public class Movie {
    private String id;

    @NotEmpty(message="Movie name cannot be empty.")
    private String name;

    public Movie() {}

    public Movie(String name) {
        this.name = name;
    }
}
