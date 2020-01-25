package org.greenrivers.spring.webfluxdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.greenrivers.spring.webfluxdemo.model.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class TaskHandler {
    private static final ThreadPoolExecutor bizThreadPoolExecutor = new ThreadPoolExecutor(
            8,
            8,
            1,
            TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(10));

    @GetMapping("tasks")
    Flux<Task> getTasks() {
        log.info("----- getTasks -----");
        return Flux.just("Task 1", "Task 2")
                .publishOn(Schedulers.fromExecutor(bizThreadPoolExecutor))
                .map(str -> {
                    log.info("map {} to Task object", str);
                    Task task = new Task();
                    task.setName(str);
                    return task;
                });
    }
}
