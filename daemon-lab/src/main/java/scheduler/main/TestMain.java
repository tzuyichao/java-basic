package scheduler.main;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import scheduler.job.DemoJob;

import java.util.concurrent.TimeUnit;

public class TestMain {
    public static void main(String[] args) throws Exception {
        JobKey testJobKey = new JobKey("test", "default");
        JobDetail testJobDetail = JobBuilder.newJob(DemoJob.class)
                .withIdentity(testJobKey)
                .usingJobData("name", "test")
                .build();

        Trigger testTrigger = TriggerBuilder.newTrigger()
                .withIdentity("testTrigger", "default")
                .usingJobData("name", "test123")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();

        Scheduler scheduler = schedulerFactory.getScheduler();

        scheduler.start();

        scheduler.scheduleJob(testJobDetail, testTrigger);

        scheduler.getJobGroupNames().forEach(System.out::println);
        Thread.sleep(10000);

        scheduler.shutdown();
    }
}
