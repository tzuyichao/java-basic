package com.example.neo4jdemo.movie.config;

import lombok.extern.java.Log;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;

@Log
@Configuration
public class DatabaseConfig {
    @Value("${org.neo4j.driver.uri}")
    private String Neo4j_URL;

    @Value("${org.neo4j.driver.authentication.username}")
    private String db_username;

    @Value("${org.neo4j.driver.authentication.password}")
    private String db_password;

    @Bean
    public org.neo4j.ogm.config.Configuration getConfiguration() {
        log.info("create neo4j configuration");
        log.info("url:" + Neo4j_URL);
        return new org.neo4j.ogm.config.Configuration.Builder().uri(Neo4j_URL).credentials(db_username, db_password).build();
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
