package com.example.heroes.controller;

import com.example.heroes.dto.Hero;
import com.example.heroes.service.HeroService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@CrossOrigin
@RestController
public class HeroController {
    private HeroService heroService;
    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping("/api/hero")
    public Collection<Hero> listAll() {
        return heroService.listAll();
    }
}
