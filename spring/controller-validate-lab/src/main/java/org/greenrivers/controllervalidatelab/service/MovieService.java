package org.greenrivers.controllervalidatelab.service;

import org.greenrivers.controllervalidatelab.model.Movie;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@Service
public class MovieService {

    public void addAll(@NotNull List<Movie> movies) {
        System.out.println("service get addAll() of " + movies.toString());
    }
}
