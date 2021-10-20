package com.example.demo;

import lombok.extern.java.Log;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Log
@RestController
public class DemoController {
    private CircuitBreakerFactory circuitBreakerFactory;
    private HttpBinService httpBinService;

    public DemoController(CircuitBreakerFactory circuitBreakerFactory, HttpBinService httpBinService) {
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.httpBinService = httpBinService;
    }

    @GetMapping("/get")
    public Map get() {
        return httpBinService.get();
    }

    @GetMapping("/delay/{seconds}")
    public Map delay(@PathVariable int seconds) {
        return circuitBreakerFactory
                .create("delay")
                .run(httpBinService.delaySupplier(seconds), error -> {
                    log.warning("delay call failed " + error);
                    Map<String, String> fallback = new HashMap<>();
                    fallback.put("hello", "world");
                    return fallback;
                });
    }
}
