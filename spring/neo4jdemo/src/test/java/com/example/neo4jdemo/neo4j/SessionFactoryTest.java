package com.example.neo4jdemo.neo4j;

import org.junit.jupiter.api.Test;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
@DataNeo4jTest
public class SessionFactoryTest {
    private SessionFactory sessionFactory;
    private Configuration configuration;

    @Autowired
    SessionFactoryTest(SessionFactory sessionFactory,
                       Configuration configuration) {
        this.sessionFactory = sessionFactory;
        this.configuration = configuration;
    }

    @Test
    void test_configuration() {
        assertNotNull(configuration);
    }

    @Test
    void test_sessionFactory_exist() {
        assertNotNull(sessionFactory);
        //sessionFactory.openSession().query()
    }

    @Test
    void test_all_shortest_paths() {
        Map<String, String> params = new HashMap<>();
        params.put("person1", "Al Pacino");
        params.put("person2", "Emil Eifrem");
        Result result = sessionFactory.openSession().query("MATCH (p1:Person { name: $person1 }),(p2:Person { name: $person2 }), p = allShortestPaths((p1)-[*]-(p2)) RETURN p", params);
        assertNotNull(result);
        for(Map<String, Object> record : result.queryResults()) {
            System.out.println(record);
        }
    }

    @Test
    void test_all_paths() {
        Map<String, String> params = new HashMap<>();
        params.put("person1", "Al Pacino");
        params.put("person2", "Emil Eifrem");
        Result result = sessionFactory.openSession().query("MATCH (p1:Person { name: $person1 }),(p2:Person { name: $person2 }), p = ((p1)-[*]-(p2)) RETURN p limit 3", params);
        assertNotNull(result);
        for(Map<String, Object> record : result.queryResults()) {
            System.out.println(record);
        }
    }
}
