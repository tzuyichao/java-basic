package com.example.neo4jdemo.movie.controller;

import com.example.neo4jdemo.movie.model.Domain;
import com.example.neo4jdemo.movie.model.DomainStatus;
import com.example.neo4jdemo.movie.repository.DomainRepository;
import org.neo4j.ogm.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/domain")
public class DomainController {
    private DomainRepository domainRepository;

    @Autowired
    public DomainController(DomainRepository domainRepository) {
        this.domainRepository = domainRepository;
    }

    @RequestMapping("/init")
    @GetMapping
    public void init() {
        Domain enterobacteriaceae = Domain.builder().name("Enterobacteriaceae").build();

        Domain oleracea = Domain.builder().name("Brassica oleracea_1").build();

        Domain brassica = Domain.builder()
                .name("Brassica_1")
                .children(List.of(oleracea)).build();

        Domain brassicaceae = Domain.builder()
                .name("Brassicaceae_1")
                .children(List.of(brassica))
                .build();
        domainRepository.save(enterobacteriaceae);
        domainRepository.save(brassicaceae);
    }

    @PostMapping("/status")
    public Result updateDomainStatus(@RequestParam("name") String name, @RequestParam("status")DomainStatus domainStatus) {
        Result result = domainRepository.updateDomainStatusCascade(name, domainStatus);
        return result;
    }
}
