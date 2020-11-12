package org.greenrivers.spring.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class PersonController {
    @PostMapping("/personPostCallable")
    public Callable<String> listPostCall() {
        log.info("----- begin personPostCallable -----");
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("{}", Thread.currentThread().getName());
                log.info("----- end personPostCallable -----");
                return "test";
            }
        };
    }
}
