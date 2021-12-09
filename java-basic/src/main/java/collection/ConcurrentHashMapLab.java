package collection;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapLab {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> movies = new ConcurrentHashMap<>();
        movies.put("JamesBond", 20);
        movies.put("Matrix", 15);
        movies.put("Harry Potter", 5);
        System.out.println(movies.mappingCount());

        long parallelismThreshold = 1;
        Optional<Integer> maxValue = Optional.ofNullable(movies.reduceValues(parallelismThreshold, Integer::max));
        System.out.println("Max Value: " + maxValue);
    }
}
