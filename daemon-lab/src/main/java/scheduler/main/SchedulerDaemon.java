package scheduler.main;

import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonInitException;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scheduler.job.DemoJob;

public class SchedulerDaemon implements Daemon {
    private static final Logger logger = LoggerFactory.getLogger(SchedulerDaemon.class);
    private Scheduler scheduler;

    @Override
    public void init(DaemonContext context) throws DaemonInitException, Exception {
        logger.info("init");

        scheduler = StdSchedulerFactory.getDefaultScheduler();
    }

    @Override
    public void start() throws Exception {
        logger.info("start");
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

        scheduler.start();
        logger.info("Starting create schedule jobs");
        scheduler.scheduleJob(testJobDetail, testTrigger);
    }

    @Override
    public void stop() throws Exception {
        logger.info("stop");
        scheduler.shutdown();
    }

    @Override
    public void destroy() {
        try {
            logger.info("destroy");
            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
