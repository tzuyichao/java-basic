package concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * from Moder Java in Action
 */
public class ParallelMain {
    private static final Logger logger = LoggerFactory.getLogger("Lab");

    public static void main(String[] args) {
        List<Shop> shopList = List.of(new Shop("Buy More"),
                new Shop("Best Buy"),
                new Shop("Best Price"),
                new Shop("EZ Shop"));

        Instant start = Instant.now();
        logger.info("starting get price");

        List<CompletableFuture<String>> priceFutures = shopList.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> {
                    try {
                        return shop.getName() + " price is " + shop.getPrice("Kindle");
                    } catch (ProductNotAvailableException e) {
                        e.printStackTrace();
                    }
                    return "";
                })).collect(Collectors.toList());
        List<String> prices = priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        prices.forEach(price -> logger.info(price));

        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        logger.info("duration: {}", duration.toMillis());

    }
}
