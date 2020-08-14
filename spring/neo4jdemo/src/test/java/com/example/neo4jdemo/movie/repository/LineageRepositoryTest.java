package com.example.neo4jdemo.movie.repository;

import com.example.neo4jdemo.movie.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.neo4j.ogm.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

@Testcontainers
@DataNeo4jTest
@ImportAutoConfiguration(classes={com.example.neo4jdemo.movie.config.DatabaseConfig.class})
public class LineageRepositoryTest {
    private LineageRepository lineageRepository;
    private ColumnUnitRepository columnUnitRepository;

    @Autowired
    public LineageRepositoryTest(LineageRepository lineageRepository, ColumnUnitRepository columnUnitRepository) {
        this.lineageRepository = lineageRepository;
        this.columnUnitRepository = columnUnitRepository;
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

    @Test
    void test_delete_model_behavior() {
        ColumnUnit source = new ColumnUnit();
        source.setName("source1");
        source.setStatus(ResourceStatus.PUBLISHED);
        ColumnUnit target = new ColumnUnit();
        target.setName("target1");
        target.setStatus(ResourceStatus.PUBLISHED);
        Lineage lineage = new Lineage();
        lineage.setSource(source);
        lineage.setTarget(target);
        lineageRepository.save(lineage, 1);
        System.out.println("source: " + source.getId());
        System.out.println("target: " + target.getId());
        System.out.println("lineage: " + lineage.getId());
        Long sourceId = source.getId();
        Long targetId = target.getId();
        Long lineageId = lineage.getId();

        lineageRepository.delete(lineage);

        Optional<ColumnUnit> sourceOptional = columnUnitRepository.findById(sourceId, 1);
        Optional<ColumnUnit> targetOptional = columnUnitRepository.findById(targetId, 1);
        if(sourceOptional.isPresent()) {
            System.out.println("source got");
        } else {
            fail("source deleted too");
        }
        if(targetOptional.isPresent()) {
            System.out.println("target got");
        } else {
            fail("target deleted too");
        }
        Optional<Lineage> lineageOptional = lineageRepository.findById(lineageId);
        if(lineageOptional.isPresent()) {
            System.out.println(">>>>>>> Big problem");
            fail("Big Problem");
        }
    }

    @Test
    void test_find_source_id_with_depth() {
        ColumnUnit source = new ColumnUnit();
        source.setName("source1");
        source.setStatus(ResourceStatus.PUBLISHED);
        ColumnUnit target = new ColumnUnit();
        target.setName("target1");
        target.setStatus(ResourceStatus.PUBLISHED);
        Lineage lineage = new Lineage();
        lineage.setSource(source);
        lineage.setTarget(target);
        lineageRepository.save(lineage, 1);
        System.out.println("source: " + source.getId());
        System.out.println("target: " + target.getId());
        System.out.println("lineage: " + lineage.getId());
        Long sourceId = source.getId();
        Long targetId = target.getId();
        Long lineageId = lineage.getId();

        Lineage[] result = lineageRepository.findBySource(sourceId);
        for(Lineage lineage1 : result) {
            System.out.println(lineage1);
            System.out.println(lineage1.getSource().getName());
            System.out.println(lineage1.getTarget().getName());
        }
//        assertThat(lineages)
//                .isNotNull()
//                .satisfies(lineages1 -> {
//                    assertThat(lineages1.length).isEqualTo(1);
//                    for(Lineage lineage1 : lineages1) {
//                        assertThat(lineage1.getSource()).isNotNull();
//                        assertThat(lineage1.getTarget()).isNotNull();
//                    }
//                });
    }
}
