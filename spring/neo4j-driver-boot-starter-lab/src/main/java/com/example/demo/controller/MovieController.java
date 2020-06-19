package com.example.demo.controller;

import com.example.demo.controller.model.MovieValueObject;
import lombok.extern.java.Log;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Log
@RestController
@RequestMapping("/movie")
public class MovieController {
    private final Driver driver;

    public MovieController(Driver driver) {
        this.driver = driver;
    }

    @RequestMapping("/titles")
    @GetMapping
    public Flux<MovieValueObject> getMovieTitle() {
        try(Session session = driver.session()) {
            return Flux.fromStream(session.run("match (m:Movie) return m order by m.name asc")
                    .list(record -> {
                        String title = record.get("m").asNode().get("title").asString();
                        MovieValueObject movie = new MovieValueObject();
                        movie.setTitle(title);
                        return movie;
                    }).stream());
        }
    }
}
