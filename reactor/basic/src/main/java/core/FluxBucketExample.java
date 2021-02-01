package core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.Arrays;

public class FluxBucketExample {
    private static final Logger log = LoggerFactory.getLogger(FluxBucketExample.class);

    public static void main(String[] args) {
        int bucketSize = 5;
        Flux.range(1, 500)
                .index()
                .scan(
                        new int[bucketSize],
                        (acc, elem) -> {
                            acc[(int)(elem.getT1()%bucketSize)] = elem.getT2();
                            return acc;
                        }
                )
                .skip(bucketSize)
                .map(array -> Arrays.stream(array).sum() * 1.0 / bucketSize)
                .subscribe(av -> log.info("Running average: {}", av));
    }
}
