package com.example.neo4jdemo.neo4j;

import org.junit.jupiter.api.Test;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
@DataNeo4jTest
public class SessionFactoryTest {
    private SessionFactory sessionFactory;

    @Autowired
    SessionFactoryTest(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Test
    void test_sessionFactory_exist() {
        assertNotNull(sessionFactory);
        //sessionFactory.openSession().query()
    }
}
