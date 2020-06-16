package com.example.helloflux.product.controller;

import com.example.helloflux.product.model.Product;
import com.example.helloflux.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public Flux<Product> getProducts() {
        return productService.getProducts();
    }

    @PostMapping("")
    public Mono<Void> createProduct(@RequestBody final Mono<Product> productMono) {
        return this.productService.createOrUpdateProduct(productMono);
    }
}
