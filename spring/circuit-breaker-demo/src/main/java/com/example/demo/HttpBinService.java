package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.function.Supplier;

@Service
public class HttpBinService {
    private RestTemplate restTemplate;

    public HttpBinService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map get() {
        return restTemplate.getForObject("https://httpbin.org/get", Map.class);
    }

    public Map delay(int seconds) {
        return restTemplate.getForObject("https://httpbin.org/delay/" + seconds, Map.class);
    }

    public Supplier<Map> delaySupplier(int seconds) {
        return () -> this.delay(seconds);
    }
}
