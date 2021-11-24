package org.acme.quickstart.service;

import org.acme.quickstart.entity.Melon;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TaiwanMelonService implements MelonService {
    @Override
    public Melon find(String name) {
        Melon melon = new Melon();
        melon.setType(name);
        melon.setWeight(5.5f);
        return melon;
    }
}
