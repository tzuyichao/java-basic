package com.example.condition.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class GreetingController {
    @GetMapping("/greeting/{name}")
    public Mono<String> greeting(@PathVariable String name) {
        log.info("invoke greeting with {}", name);
        return Mono.just("Greeting! " + name);
    }
}
