package scheduler.discover;

public interface JobDiscoverer<T> {
    Class[] find();
}
