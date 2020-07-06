package com.example.neo4jdemo.movie.controller;

import com.example.neo4jdemo.movie.model.Domain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DomainControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testRoot() {
        ResponseEntity<Collection> result = restTemplate.getForEntity("/domain/root", Collection.class);
        System.out.println(result.getBody());
    }

}
