package com.example.heroes.event;

import com.example.heroes.dto.Hero;
import com.example.heroes.repository.HeroRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class StartupApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    private HeroRepository heroRepository;
    public StartupApplicationListener(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.info("Doing...");
        Hero nice = new Hero("Dr Nice");
        Hero narco = new Hero("Narco");
        Hero bombasto = new Hero("Bombasto");
        Hero celeritas = new Hero("Celeritas");
        Hero magneta = new Hero("Magneta");
        Hero rubberMan = new Hero("RubberMan");
        Hero dynama = new Hero("Dynama");
        Hero iq = new Hero("Dr IQ");
        Hero magma = new Hero("Magma");
        Hero tornado = new Hero("Tornado");

        heroRepository.saveAll(List.of(nice, narco, bombasto, celeritas, magma, magneta, rubberMan, dynama, iq, tornado));
    }
}
