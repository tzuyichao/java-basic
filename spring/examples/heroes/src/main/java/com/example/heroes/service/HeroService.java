package com.example.heroes.service;

import com.example.heroes.dto.Hero;

import java.util.Collection;

public interface HeroService {
    Collection<Hero> listAll();
}
