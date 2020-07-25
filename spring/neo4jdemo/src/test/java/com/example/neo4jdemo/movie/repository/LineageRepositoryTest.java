package com.example.neo4jdemo.movie.repository;

import com.example.neo4jdemo.movie.model.CatalogUnit;
import com.example.neo4jdemo.movie.model.ColumnUnit;
import com.example.neo4jdemo.movie.model.Lineage;
import com.example.neo4jdemo.movie.model.SystemUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
@DataNeo4jTest
@ImportAutoConfiguration(classes={com.example.neo4jdemo.movie.config.DatabaseConfig.class})
public class LineageRepositoryTest {
    private LineageRepository lineageRepository;

    @Autowired
    public LineageRepositoryTest(LineageRepository lineageRepository) {
        this.lineageRepository = lineageRepository;
    }

    @Test
    public void test_non_columnunit_add_lineage_failed() {
        Assertions.assertThrows(ClassCastException.class, () -> {
            CatalogUnit source = new SystemUnit();
            source.setName("systemUnit1");
            ColumnUnit target = new ColumnUnit();
            target.setName("Column Unit 1");

            Lineage lineage = new Lineage();
            lineage.setSource((ColumnUnit) source);
            lineage.setTarget(target);

            lineageRepository.save(lineage);
            System.out.println(lineage.getId());
        });
    }

    @Test
    public void test_add_lineage_happy() {
        ColumnUnit source = new ColumnUnit();
        source.setName("Column Unit source");
        ColumnUnit target = new ColumnUnit();
        target.setName("Column Unit target");

        Lineage lineage = new Lineage();
        lineage.setSource(source);
        lineage.setTarget(target);

        lineageRepository.save(lineage);
        System.out.println(source.getId());
        System.out.println(target.getId());
        System.out.println(lineage.getId());
        assertNotNull(source.getId());
        assertNotNull(target.getId());
        assertNotNull(lineage.getId());
    }
}
