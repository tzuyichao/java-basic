package com.deltaww.schedule.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class ThreadPoolTaskSchedulerExamples implements DisposableBean {
    private ThreadPoolTaskScheduler taskScheduler;

    private CronTrigger cronTrigger;

    private PeriodicTrigger periodicTrigger;

    public ThreadPoolTaskSchedulerExamples(ThreadPoolTaskScheduler taskScheduler, CronTrigger cronTrigger, PeriodicTrigger periodicTrigger) {
        this.taskScheduler = taskScheduler;
        this.cronTrigger = cronTrigger;
        this.periodicTrigger = periodicTrigger;
    }

    @PostConstruct
    public void scheduleRunnableWithCronTrigger() {
        taskScheduler.schedule(new RunnableTask("Current Date"), new Date());
        taskScheduler.scheduleWithFixedDelay(new RunnableTask("Fixed 1 second Delay"), 1000);
        taskScheduler.scheduleWithFixedDelay(new RunnableTask("Current Date Fixed 1 second Delay"), new Date(), 1000);
        taskScheduler.scheduleAtFixedRate(new RunnableTask("Fixed Rate of 2 seconds"), new Date(), 2000);
        taskScheduler.scheduleAtFixedRate(new RunnableTask("Fixed Rate of 2 seconds"), 2000);
        taskScheduler.schedule(new RunnableTask("Cron Trigger"), cronTrigger);
        taskScheduler.schedule(new RunnableTask("Periodic Trigger"), periodicTrigger);
    }

    @Override
    public void destroy() throws Exception {
        if(taskScheduler != null) {
            log.info("Shutting down the Task Scheduler");
            taskScheduler.shutdown();
        }
    }

    class RunnableTask implements Runnable {

        private String message;

        public RunnableTask(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            System.out.println("Runnable Task with " + message + " on thread " + Thread.currentThread().getName());
        }
    }
}
