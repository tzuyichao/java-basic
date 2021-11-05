package com.example.neo4jdemo.util;

import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;

import java.io.IOException;

/**
 * https://stackoverflow.com/questions/24047500/how-do-i-scan-for-annotation-in-spring-web-app-using-reflections-library-or-oth
 */
@Log
public class AnnotationLab {
    @Test
    void test_lookup_entity() throws IOException {
        SimpleMetadataReaderFactory metadataFactory = new SimpleMetadataReaderFactory();
        ResourcePatternResolver scaner = new PathMatchingResourcePatternResolver();
        Resource[] resources = scaner.getResources("classpath*:/com/example/neo4jdemo/movie/model/**/*.class");
        for (Resource r : resources) {
            log.info("Scanning [" + r.getDescription() + "]");
            MetadataReader metadataReader = metadataFactory.getMetadataReader(r);
            if (metadataReader.getAnnotationMetadata().isAnnotated("org.neo4j.ogm.annotation.NodeEntity")) {
                log.info("found annotation in [" + r.getDescription() + "]");

            }
        }
    }
}
