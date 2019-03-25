package scheduler.main;

import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonInitException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SchedulerDaemon implements Daemon {
    private static final Logger logger = LoggerFactory.getLogger(SchedulerDaemon.class);
    private Scheduler scheduler;

    @Override
    public void init(DaemonContext context) throws DaemonInitException, Exception {
        System.out.println("init");
        scheduler = StdSchedulerFactory.getDefaultScheduler();
        System.out.println(scheduler.toString());
        scheduler.getJobGroupNames().forEach(System.out::println);
    }

    @Override
    public void start() throws Exception {
        System.out.println("start");
        scheduler.start();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("stop");
        scheduler.shutdown();
    }

    @Override
    public void destroy() {
        try {
            System.out.println("destroy");
            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
