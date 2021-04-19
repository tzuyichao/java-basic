package com.example.cloud.movie.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {
    @GetMapping("/movie/info")
    public String info() {
        return "UP";
    }
}
