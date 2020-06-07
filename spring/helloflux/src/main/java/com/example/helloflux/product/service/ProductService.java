package com.example.helloflux.product.service;

import com.example.helloflux.product.model.Product;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ProductService {
    private final Map<String, Product> products = new ConcurrentHashMap<>();

    public ProductService() {
        products.put("Unity01", new Product(1L, "Unity 101"));
    }

    public Flux<Product> getProducts() {
        return Flux.fromIterable(products.values());
    }
}
