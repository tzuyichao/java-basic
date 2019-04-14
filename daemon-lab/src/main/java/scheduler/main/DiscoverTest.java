package scheduler.main;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scheduler.annotation.JobExec;
import scheduler.discover.JobDiscoverer;
import scheduler.discover.QuartzJobDiscoverer;

public class DiscoverTest {
    private static final Logger logger = LoggerFactory.getLogger(DiscoverTest.class);

    public static void main(String[] args) throws SchedulerException {
        JobDiscoverer jobDiscoverer = new QuartzJobDiscoverer("scheduler.annotated.job");
        Class[] jobs = jobDiscoverer.find();
        System.out.println("Jobs: " + jobs.length);
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        for(Class job: jobs) {
            JobExec jobExecAnnotation = (JobExec) job.getAnnotation(JobExec.class);
            printJobExec(jobExecAnnotation);
            JobKey testJobKey = new JobKey(jobExecAnnotation.name(), jobExecAnnotation.group());
            JobDetail testJobDetail = JobBuilder.newJob(job)
                    .withIdentity(testJobKey)
                    .usingJobData("name", jobExecAnnotation.name())
                    .build();

            Trigger testTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(jobExecAnnotation.name(), jobExecAnnotation.group())
                    .usingJobData("name", jobExecAnnotation.name())
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                    .build();
            scheduler.scheduleJob(testJobDetail, testTrigger);
        }
        System.out.println("Waiting...");
        for(String jobName: scheduler.getJobGroupNames()) {
            System.out.println(jobName);
        }
        try {
            Thread.sleep(40000);
        } catch(InterruptedException e) {
        }
        scheduler.getListenerManager()
                .addSchedulerListener(new SchedulerListener() {
                    @Override
                    public void jobScheduled(Trigger trigger) {

                    }

                    @Override
                    public void jobUnscheduled(TriggerKey triggerKey) {

                    }

                    @Override
                    public void triggerFinalized(Trigger trigger) {

                    }

                    @Override
                    public void triggerPaused(TriggerKey triggerKey) {

                    }

                    @Override
                    public void triggersPaused(String triggerGroup) {

                    }

                    @Override
                    public void triggerResumed(TriggerKey triggerKey) {

                    }

                    @Override
                    public void triggersResumed(String triggerGroup) {

                    }

                    @Override
                    public void jobAdded(JobDetail jobDetail) {

                    }

                    @Override
                    public void jobDeleted(JobKey jobKey) {

                    }

                    @Override
                    public void jobPaused(JobKey jobKey) {

                    }

                    @Override
                    public void jobsPaused(String jobGroup) {

                    }

                    @Override
                    public void jobResumed(JobKey jobKey) {

                    }

                    @Override
                    public void jobsResumed(String jobGroup) {

                    }

                    @Override
                    public void schedulerError(String msg, SchedulerException cause) {

                    }

                    @Override
                    public void schedulerInStandbyMode() {

                    }

                    @Override
                    public void schedulerStarted() {

                    }

                    @Override
                    public void schedulerStarting() {

                    }

                    @Override
                    public void schedulerShutdown() {
                        logger.info("scheduler shutdown");
                    }

                    @Override
                    public void schedulerShuttingdown() {

                    }

                    @Override
                    public void schedulingDataCleared() {

                    }
                });
        scheduler.shutdown();
    }

    public static void printJobExec(JobExec jobExecAnnotation) {
        System.out.println(jobExecAnnotation.name());
        System.out.println(jobExecAnnotation.group());
        System.out.println(jobExecAnnotation.cronExpression());
    }
}
