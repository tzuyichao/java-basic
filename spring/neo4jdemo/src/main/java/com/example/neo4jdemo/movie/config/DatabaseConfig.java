package com.example.neo4jdemo.movie.config;

import com.example.neo4jdemo.movie.converter.DomainStatusToStringConverter;
import com.example.neo4jdemo.movie.converter.StringToDomainStatusConverter;
import lombok.extern.java.Log;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.data.neo4j.conversion.MetaDataDrivenConversionService;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;

@Log
@ComponentScan(value={"com.example.neo4jdemo.movie.converter"})
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

    @Autowired
    private StringToDomainStatusConverter stringToDomainStatusConverter;

    @Autowired
    private DomainStatusToStringConverter domainStatusToStringConverter;


    @Bean
    protected ConversionService neo4jConversionService() throws Exception {
        ConverterRegistry converterRegistry = new MetaDataDrivenConversionService(sessionFactory().metaData());
        converterRegistry.addConverter(stringToDomainStatusConverter);
        converterRegistry.addConverter(domainStatusToStringConverter);
        return (ConversionService) converterRegistry;
    }
}
