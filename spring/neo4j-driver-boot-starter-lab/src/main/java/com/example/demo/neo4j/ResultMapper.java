package com.example.demo.neo4j;

import lombok.Builder;
import org.springframework.context.annotation.AnnotationConfigUtils;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;

@Builder
public final class ResultMapper {
    private final String scanPackage;

    public Map<String, Class> resolve(String scanPackage) throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = scanPackage.replace('.', '/');
        Enumeration<URL> resourceEnumeration = classLoader.getResources(path);
        while(resourceEnumeration.hasMoreElements()) {
            URL resource = resourceEnumeration.nextElement();
            System.out.println(resource.getFile());
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        ResultMapper resultMapper = ResultMapper.builder().scanPackage("com.example.demo.controller.model").build();
        resultMapper.resolve(resultMapper.scanPackage);
    }
}
