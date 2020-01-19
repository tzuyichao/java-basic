package org.greenrivers.springasynclab.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SimpleService {
    @Async
    public String doSomethingA() {
        log.info("doSomethingA {}", Thread.currentThread().getName());
        return "doSomethingA";
    }
}
