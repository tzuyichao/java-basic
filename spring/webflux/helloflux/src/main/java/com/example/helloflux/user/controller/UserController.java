package com.example.helloflux.user.controller;

import com.example.helloflux.user.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class UserController {
    @GetMapping("/hello/{name}")
    public Mono<User> hello(@PathVariable("name") String name) {
        return Mono.just(new User(name));
    }
}
