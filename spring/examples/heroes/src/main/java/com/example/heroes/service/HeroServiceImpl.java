package com.example.heroes.service;

import com.example.heroes.dto.Hero;
import com.example.heroes.repository.HeroRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class HeroServiceImpl implements HeroService {
    private HeroRepository heroRepository;
    private HeroServiceImpl(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    @Override
    public Collection<Hero> listAll() {
        return StreamSupport.stream(heroRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }
}
