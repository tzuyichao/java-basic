package org.greenrivers.springasynclab.controller;

import lombok.extern.slf4j.Slf4j;
import org.greenrivers.springasynclab.service.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SimpleController {
    private SimpleService simpleService;

    @Autowired
    public SimpleController(SimpleService simpleService) {
        this.simpleService = simpleService;
    }

    @GetMapping("/basic")
    public String basic() {
        log.info("{}", Thread.currentThread().getName());
        log.info("{}", simpleService.doSomethingA());
        return "done";
    }
}
