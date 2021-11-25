package org.acme.quickstart.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class RecommendationService {
    private List<String> products;

    @PostConstruct
    public void init() {
        products = Arrays.asList("Orange", "Apple", "Mango");
        System.out.println("Product initialized");
    }

    @PreDestroy
    public void cleanup() {
        products = null;
        System.out.println("Product cleaned up");
    }

    public List<String> getProducts() {
        return products;
    }
}
