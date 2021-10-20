package com.example.neo4jdemo.movie.controller;

import com.example.neo4jdemo.movie.model.CatalogUnitType;
import com.example.neo4jdemo.movie.model.Domain;
import com.example.neo4jdemo.movie.model.DomainStatus;
import com.example.neo4jdemo.movie.repository.DomainRepository;
import com.example.neo4jdemo.movie.service.CatalogUnitService;
import com.example.neo4jdemo.movie.service.DomainService;
import lombok.Builder;
import lombok.Data;
import lombok.extern.java.Log;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Path;
import org.neo4j.ogm.model.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Log
@RestController
@RequestMapping("/domain")
public class DomainController {
    public static final String ROOT_DOMAIN_NAME = "Glossary";
    private DomainRepository domainRepository;
    private DomainService domainService;

    public DomainController(DomainRepository domainRepository, DomainService domainService) {
        this.domainRepository = domainRepository;
        this.domainService = domainService;
    }

    @GetMapping("init")
    public void init(HttpServletResponse response) {
        List<Domain> roots = domainRepository.findByNameAndStatus(ROOT_DOMAIN_NAME, DomainStatus.OPEN, 1);
        if(roots.size() == 0) {
            Domain root = Domain.builder()
                    .name(ROOT_DOMAIN_NAME)
                    .status(DomainStatus.OPEN)
                    .build();
            domainRepository.save(root);
            response.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    @RequestMapping("/initDemo")
    @GetMapping
    public void initDemo() {
        Domain enterobacteriaceae = Domain.builder().name("Enterobacteriaceae_1").status(DomainStatus.DRAFT).build();

        Domain oleracea = Domain.builder().name("Brassica oleracea_1").status(DomainStatus.DRAFT).build();

        Domain brassica = Domain.builder()
                .name("Brassica_1")
                .status(DomainStatus.DRAFT)
                .children(List.of(oleracea)).build();

        Domain brassicaceae = Domain.builder()
                .name("Brassicaceae_1")
                .status(DomainStatus.DRAFT)
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

    @GetMapping("/root")
    public Collection<Domain> root() {
        return domainRepository.rootDomains();
    }

    @GetMapping("/{id}")
    public Domain getDomain(@PathVariable("id") Long id, HttpServletResponse response) {
        Optional<Domain> domainOptional = domainRepository.findById(id);
        if(domainOptional.isPresent()) {
            return domainOptional.get();
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }

    @GetMapping("/catalog")
    public void getCatalogUnitService() {
        CatalogUnitService dbService = domainService.lookup(CatalogUnitType.DB);
        log.info(dbService.toString());
    }

    @GetMapping("/graph")
    public void graph() {
        Result result = domainRepository.paths("Brassica oleracea_1", "Brassicaceae_1");
        Iterator<Map<String, Object>> iterator = result.iterator();
        Map<Long, Node> nodes = new HashMap<>();
        List<Link> links = new ArrayList<>();
        while(iterator.hasNext()) {
            Map<String, Object> r = iterator.next();
            Object payload = r.get("p");
            System.out.println(payload instanceof Path.Segment[]);
            Path.Segment[] pathArray = (Path.Segment[])payload;
            for(Path.Segment segment : pathArray) {
                Node start = segment.start();
                Node end = segment.end();
                if(!nodes.containsKey(start.id())) {
                    nodes.put(start.id(), start);
                }
                if(!nodes.containsKey(end.id())) {
                    nodes.put(end.id(), end);
                }
                links.add(Link.builder().id(segment.relationship().id()).start(segment.relationship().startNodeId()).end(segment.relationship().endNodeId()).build());
            }
        }
        log.info("nodes: " + nodes);
        log.info("links: " + links);
    }

    @Builder
    @Data
    static class Link {
        private Long start;
        private Long end;
        private Long id;
    }

    @DeleteMapping("/clean")
    public void clean() {
        domainRepository.deleteAll();
    }
}
