package org.greenrivers.spring.webfluxdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.greenrivers.spring.webfluxdemo.model.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * from Java Asynchronous Programming in Action Chapter 7
 */
@Slf4j
@RestController
public class PersonHandler {
    @GetMapping("/person")
    Mono<String> getPerson() {
        log.info("getPerson called");
        return Mono.just("jiaduo");
    }

    @GetMapping("/people")
    Flux<Person> getPeople() {
        log.info("getPeople called");
        return Flux.just("jiaduo", "mark", "johnny")
                .publishOn(Schedulers.elastic())
                .map(str -> {
                    log.info("map to Person object");
                    Person person = new Person();
                    person.setName(str);
                    return person;
                });
    }
}
