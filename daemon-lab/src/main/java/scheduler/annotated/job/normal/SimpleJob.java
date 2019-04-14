package scheduler.annotated.job.normal;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import scheduler.annotation.JobExec;

@JobExec(name = "Job1", group = "default", cronExpression="0/5 * * * * ?")
public class SimpleJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

    }
}
