package com.example.neo4jdemo.movie.repository;

import com.example.neo4jdemo.movie.model.Domain;
import com.example.neo4jdemo.movie.model.DomainStatus;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.types.Path;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.*;

import static com.example.neo4jdemo.util.Neo4jUtil.printSegment;
import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@DataNeo4jTest
@ImportAutoConfiguration(classes={com.example.neo4jdemo.movie.config.DatabaseConfig.class})
public class DomainRepositoryTest {
    private DomainRepository domainRepository;
    private SessionFactory sessionFactory;

    @Autowired
    public DomainRepositoryTest(DomainRepository domainRepository, SessionFactory sessionFactory) {
        this.domainRepository = domainRepository;
        this.sessionFactory = sessionFactory;
    }

    @BeforeEach
    void init() {
        Domain enterobacteriaceae = Domain.builder().name("Enterobacteriaceae").status(DomainStatus.OPEN).build();

        Domain oleracea = Domain.builder().name("Brassica oleracea").status(DomainStatus.OPEN).build();

        Domain brassica = Domain.builder()
                .name("Brassica")
                .status(DomainStatus.OPEN)
                .children(List.of(oleracea)).build();

        Domain brassicaceae = Domain.builder()
                .name("Brassicaceae")
                .status(DomainStatus.OPEN)
                .children(List.of(brassica))
                .build();
        domainRepository.save(enterobacteriaceae);
        domainRepository.save(brassicaceae);
    }

    @Test
    void test_count_query_method() {
        Long count = domainRepository.countByNameAndStatus("Brassicaceae", DomainStatus.OPEN);
        assertEquals(1L, count);
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
    void test_find_domain_by_name_and_status_depth_1() {
        List<Domain> domainList = domainRepository.findByNameAndStatus("Brassicaceae", DomainStatus.OPEN, 1);
        assertEquals(1, domainList.size());
        assertEquals(1, domainList.get(0).getChildren().size());
    }

    @Test
    void test_find_domain_by_name_and_status_depth_0() {
        List<Domain> domainList = domainRepository.findByNameAndStatus("Brassicaceae", DomainStatus.OPEN, 0);
        assertEquals(1, domainList.size());
        assertEquals(1, domainList.get(0).getChildren().size());
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
    void test_available_name_using_parent_id() {
        Collection<Domain> parents = domainRepository.findByName("Brassicaceae");
        assertSame(1, parents.size());
        Domain parent = (Domain) parents.stream().toArray()[0];
        boolean result = domainRepository.checkDomainName(parent.getId(), "bbbb");
        assertTrue(result);
    }

    @Test
    void test_get_parent_on_root() {
        Collection<Domain> parents = domainRepository.findByName("Enterobacteriaceae");
        Domain parent = (Domain) parents.stream().toArray()[0];
        Domain should_not_exist = domainRepository.parentDomain(parent.getId());
        assertNull(should_not_exist);
    }

    @Test
    void test_get_parent() {
        Collection<Domain> brassicas = domainRepository.findByName("Brassica");
        Domain brassica = (Domain) brassicas.stream().toArray()[0];
        Collection<Domain> brassicaceaes = domainRepository.findByName("Brassicaceae");
        Domain brassicaceae = (Domain) brassicaceaes.stream().toArray()[0];

        Domain should_be_brassicaceae = domainRepository.parentDomain(brassica.getId());
        assertSame(should_be_brassicaceae.getId(), brassicaceae.getId());
    }

//    @Test
//    void test_update_domain_status_cascade() {
//        Result result = domainRepository.updateDomainStatusCascade("Brassicaceae", DomainStatus.DRAFT);
//        assertEquals(4, result.queryStatistics().getPropertiesSet());
//        Collection<Domain> brassicas = domainRepository.findByName("Brassica");
//        Domain brassica = (Domain) brassicas.stream().toArray()[0];
//        System.out.println(brassica.getStatus());
//        assertTrue(brassica.getStatus() == DomainStatus.DRAFT);
//    }


    @Test
    void test_query_parameter() {
        Domain enterobacteriaceae = new Domain();
        enterobacteriaceae.setName("Enterobacteriaceae");
        Optional<Domain> result = domainRepository.searchTest(enterobacteriaceae);
        System.out.println(result);
    }

    @Test
    void test_paths() {
        Result result = domainRepository.paths("Brassica oleracea", "Brassicaceae");
        System.out.println(result);
        assertNotNull(result);
        Iterator<Map<String, Object>> iterator = result.iterator();
        while (iterator.hasNext()) {
            Map<String, Object> map = iterator.next();
            System.out.println(map);
            System.out.println(map.get("p") instanceof Path.Segment[]);
            if(map.get("p") instanceof Path.Segment[]) {
                for (Path.Segment segment : (Path.Segment[]) map.get("p")) {
                    System.out.println(segment);
                    printSegment(segment);
                }
            }
        }
    }

    @Ignore
    @Test()
    void test_path_query() {
        Map<String, String> params = new HashMap<>();
        params.put("rootName", "Brassicaceae_1");
        params.put("sourceName", "Brassica oleracea_1");

        Session session = sessionFactory.openSession();
        Result result = session.query("match p=((:Domain {name: $sourceName})-[:GLOSSARY_HIERARCHY*]->(:Domain {name: $rootName})) return p", params, true);
        assertNotNull(result);
        Iterator<Map<String, Object>> iterator = result.iterator();
        while (iterator.hasNext()) {
            Map<String, Object> map = iterator.next();
            System.out.println(map);
            System.out.println(map.get("p") instanceof Path.Segment[]);
            if(map.get("p") instanceof Path.Segment[]) {
                for (Path.Segment segment : (Path.Segment[]) map.get("p")) {
                    System.out.println(segment);
                    printSegment(segment);
                }
            }
        }
    }

    @Test
    void test_checkDomainExist_not_exist() {
        boolean shouldNotExist = domainRepository.checkDomainExist(10000L);
        assertFalse(shouldNotExist);
    }

    @Test
    void test_checkDomainExist_true() {
        Collection<Domain> brassicaceaes = domainRepository.findByName("Brassicaceae");
        for(Domain brassicaceae: brassicaceaes) {
            boolean shouldExist = domainRepository.checkDomainExist(brassicaceae.getId());
            assertTrue(shouldExist);
        }
    }

    @Test
    void test_deleteOtherDomainRelationship() {
        Collection<Domain> brassicas = domainRepository.findByName("Brassica");
        Domain brassica = brassicas.toArray(new Domain[0])[0];
        Result result = domainRepository.deleteOtherDomainRelationship(brassica.getId());
        System.out.println(result.queryStatistics());
        Optional<Domain> newDomainOptional = domainRepository.findById(brassica.getId());
        System.out.println(newDomainOptional.get().getChildren());
    }

    @AfterEach
    void clean() {
        domainRepository.deleteAll();
    }
}
