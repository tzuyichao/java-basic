package concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * from Moder Java in Action
 */
public class ParallelMain2 {
    private static final Logger logger = LoggerFactory.getLogger("Lab");
    private static final String PRODUCT = "kindle";

    public static void main(String[] args) {
        List<Shop> shopList = List.of(new Shop("Buy More"),
                new Shop("Best Buy"),
                new Shop("Best Price"),
                new Shop("EZ Shop"));

        Instant start = Instant.now();
        logger.info("starting get price");

        Map<String, Future<Double>> priceFutures = shopList.stream()
                .collect(Collectors.toMap(shop -> shop.getName(), shop -> shop.getPriceAsync(PRODUCT)));

        priceFutures.forEach((key, val) -> {
            try {
                logger.info("{} prices is {}", key, val.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        logger.info("duration: {}", duration.toMillis());

    }
}
