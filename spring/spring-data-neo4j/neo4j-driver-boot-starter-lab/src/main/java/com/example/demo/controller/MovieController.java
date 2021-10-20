package com.example.demo.controller;

import com.example.demo.controller.model.MovieValueObject;
import com.example.demo.neo4j.ResultMapper;
import lombok.extern.java.Log;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.Value;
import org.neo4j.driver.internal.value.PathValue;
import org.neo4j.driver.reactive.RxSession;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Path;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;

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
                        return MovieValueObject.builder()
                                .title(title)
                                .build();
                    }).stream());
        }
    }

    @RequestMapping("/movies")
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getMovieTitles() {
        return Flux.usingWhen(Mono.fromSupplier(() -> driver.rxSession()),
                s -> Flux.from(s.run("MATCH (m:Movie) RETURN m ORDER BY m.name ASC").records()),
                RxSession::close)
                .map(record -> record.get("m").asNode().get("title").asString());
    }

    /**
     * TODO: finish me
     * @param start
     * @param end
     * @return
     */
    @RequestMapping("/shortestpaths")
    @GetMapping
    public Flux<String> allShortestPaths(@RequestParam(name="start") String start,
                                         @RequestParam(name="end") String end) {
        try(Session session = driver.session()) {
            Map<String, Object> params = new HashMap<>();
            params.put("person1", start);
            params.put("person2", end);
            List<String> paths = new ArrayList<>();
            session.run("MATCH (p1:Person { name: $person1 }),(p2:Person { name: $person2 }), p = allShortestPaths((p1)-[*]-(p2)) RETURN p", params)
                    .list(record -> {
                        System.out.println(record);
                        for(Value value : record.values()) {
                            System.out.println(value instanceof PathValue);
                            if(value instanceof PathValue) {
                                Path path = value.asPath();
                                System.out.println(path);
                                Iterator<Path.Segment> pathIterator = path.iterator();
                                while(pathIterator.hasNext()) {
                                    Path.Segment segment = pathIterator.next();
                                    Node startNode = segment.start();
                                    ResultMapper resultMapper = new ResultMapper();

                                    System.out.println(segment.start().id() + " (" + segment.start().get("name").asString() + ") -> " + segment.end().id() + " (" + segment.end().get("name").asString() + ")");
                                    paths.add(segment.toString());
                                }
                            }
                        }
                        return record;
                    });
            return Flux.fromStream(paths.stream());
        }
        //return Flux.empty();
    }
}
