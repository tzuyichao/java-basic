package scheduler.annotated.job.normal;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scheduler.annotation.JobExec;

@JobExec(name = "Job1", group = "default", cronExpression="0/5 * * * * ?")
public class SimpleJob implements Job {
    private static final Logger logger = LoggerFactory.getLogger(SimpleJob.class);
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("Job Running");
    }
}
