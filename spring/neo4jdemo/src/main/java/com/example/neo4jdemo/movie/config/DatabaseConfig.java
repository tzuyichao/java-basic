package com.example.neo4jdemo.movie.config;

import lombok.extern.java.Log;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;

@Log
@Configuration
public class DatabaseConfig {
    public static final String Neo4j_URL = "bolt://localhost:7687";

    @Bean
    public org.neo4j.ogm.config.Configuration getConfiguration() {
        log.info("create neo4j configuration");
        return new org.neo4j.ogm.config.Configuration.Builder().uri(Neo4j_URL).credentials("neo4j", "movies").build();
    }

    @Bean
    public SessionFactory sessionFactory() {
        log.info("create neo4j sessionFactory");
        return new SessionFactory(getConfiguration(),
                "com.example.neo4jdemo.movie.model");
    }

    @Bean
    public Neo4jTransactionManager transactionManager() {
        log.info("create neo4j transaction manager");
        return new Neo4jTransactionManager(sessionFactory());
    }
}
