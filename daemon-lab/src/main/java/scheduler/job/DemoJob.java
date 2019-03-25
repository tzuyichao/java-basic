package scheduler.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoJob implements Job {
    private static final Logger logger = LoggerFactory.getLogger(DemoJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("DemoJob running... JobDetail name=" + context.getJobDetail().getJobDataMap().getString("name"));
        System.out.println("DemoJob running... Trigger name=" + context.getTrigger().getJobDataMap().getString("name"));
        logger.info("DemoJob running...");
        logger.info("name: {}", context.getJobDetail().getJobDataMap().getString("name"));
    }
}
