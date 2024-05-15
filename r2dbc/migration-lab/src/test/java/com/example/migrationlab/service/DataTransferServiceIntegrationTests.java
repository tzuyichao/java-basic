package com.example.migrationlab.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

@SpringBootTest
public class DataTransferServiceIntegrationTests {
    @Autowired
    private DataTransferService dataTransferService;

    @Test
    public void testTransferData() {
        StepVerifier.create(dataTransferService.transferData())
                .expectSubscription()
                .expectComplete()
                .verify();
    }
}
