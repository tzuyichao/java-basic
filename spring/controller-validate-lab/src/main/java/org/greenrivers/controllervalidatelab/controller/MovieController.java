package org.greenrivers.controllervalidatelab.controller;

import org.greenrivers.controllervalidatelab.model.Movie;
import org.greenrivers.controllervalidatelab.validator.MaxSizeConstraint;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Validated
@RestController
@RequestMapping("/movies")
public class MovieController {

    @PostMapping
    public void addAll(
            @RequestBody @NotEmpty @MaxSizeConstraint
            List<Movie> movies) {
        System.out.println("add " + movies.toString());
    }
}
