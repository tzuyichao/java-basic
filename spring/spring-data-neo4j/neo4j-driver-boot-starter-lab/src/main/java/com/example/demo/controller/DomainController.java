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
@RequestMapping("/domain")
public class DomainController {
    private final Driver driver;

    public DomainController(Driver driver) {
        this.driver = driver;
    }

    @RequestMapping("/path")
    @GetMapping
    public Flux<String> getMovieTitle() {
        try(Session session = driver.session()) {
            return Flux.fromStream(session.run("match p=((:Domain {name: 'Brassica oleracea_1'})-[:GLOSSARY_HIERARCHY*]->(:Domain {name: 'Brassicaceae_1'})) return p")
                    .list(record -> {
                        return record.toString();
                    }).stream());
        }
    }
}
