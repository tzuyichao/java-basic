package com.example.neo4jdemo.movie.repository;

import com.example.neo4jdemo.movie.model.SystemUnit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.HashMap;
import java.util.Map;

@Testcontainers
@DataNeo4jTest
@ImportAutoConfiguration(classes={com.example.neo4jdemo.movie.config.DatabaseConfig.class})
public class SystemUnitRepositoryTest {
    private SystemUnitRepository systemUnitRepository;

    @Autowired
    public SystemUnitRepositoryTest(SystemUnitRepository systemUnitRepository) {
        this.systemUnitRepository = systemUnitRepository;
    }

    @Test
    void save_SystemUnit() {
        SystemUnit systemUnit = new SystemUnit();
        systemUnit.setName("Test");
        Map<String, Object> properties = new HashMap<>();
        properties.put("category", "Configuration");
        properties.put("layer", 10L);
        systemUnit.setProperties(properties);
        systemUnitRepository.save(systemUnit);
    }
}

