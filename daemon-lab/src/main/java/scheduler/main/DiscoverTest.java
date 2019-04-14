package scheduler.main;

import scheduler.discover.JobDiscoverer;
import scheduler.discover.QuartzJobDiscoverer;

public class DiscoverTest {
    public static void main(String[] args) {
        JobDiscoverer jobDiscoverer = new QuartzJobDiscoverer("scheduler.annotated.job");
        Class[] jobs = jobDiscoverer.find();
        System.out.println("Jobs: " + jobs.length);
    }
}
