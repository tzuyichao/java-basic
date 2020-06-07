package com.example.helloflux.product.controller;

import com.example.helloflux.product.model.Product;
import com.example.helloflux.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public Flux<Product> getProducts() {
        return productService.getProducts();
    }
}
