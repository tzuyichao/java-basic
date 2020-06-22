package com.example.neo4jdemo.movie.repository;

import com.example.neo4jdemo.movie.model.Domain;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@DataNeo4jTest
@ImportAutoConfiguration(classes={com.example.neo4jdemo.movie.config.DatabaseConfig.class})
public class DomainRepositoryTest {
    private DomainRepository domainRepository;

    @Autowired
    public DomainRepositoryTest(DomainRepository domainRepository) {
        this.domainRepository = domainRepository;
    }

    @BeforeEach
    void init() {
        Domain enterobacteriaceae = Domain.builder().name("Enterobacteriaceae").build();

        Domain brassica = Domain.builder().name("Brassica").build();

        Domain brassicaceae = Domain.builder()
                .name("Brassicaceae")
                .children(List.of(brassica))
                .build();
        domainRepository.save(enterobacteriaceae);
        domainRepository.save(brassicaceae);
    }

    @Test
    void test_root() {
        Collection<Domain> roots = domainRepository.rootDomains();
        assertSame(2, roots.size());
        roots.forEach(domain -> {
            System.out.println(domain.getName());
        });
    }

    @Test
    void test_not_available_name () {
        boolean result = domainRepository.checkDomainName("Brassicaceae", "Brassica");
        assertFalse(result);
    }

    @Test
    void test_available_name () {
        boolean result = domainRepository.checkDomainName("Brassicaceae", "bbbb");
        assertTrue(result);
    }

    @Test
    void test_available_name_using_parent_id () {
        Collection<Domain> parents = domainRepository.findByName("Brassicaceae");
        assertSame(1, parents.size());
        Domain parent = (Domain) parents.stream().toArray()[0];
        boolean result = domainRepository.checkDomainName(parent.getId(), "bbbb");
        assertTrue(result);
    }

    @AfterEach
    void clean() {
        domainRepository.deleteAll();
    }
}
