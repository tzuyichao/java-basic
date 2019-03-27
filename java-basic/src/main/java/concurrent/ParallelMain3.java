package concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ParallelMain3 {
    private static final Logger logger = LoggerFactory.getLogger("Lab");

    public static void main(String[] args) {
        final List<Shop> shopList = List.of(new Shop("Buy More"),
                new Shop("Best Buy"),
                new Shop("Best Price"),
                new Shop("EZ Shop"));

        final Executor executor = Executors.newFixedThreadPool(Math.min(shopList.size(), 100), (Runnable r) -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        });

        Instant start = Instant.now();
        List<CompletableFuture<String>> futures = shopList.stream().map(shop ->
                CompletableFuture.supplyAsync(() -> {
                    try {
                        return shop.getName() + " price is " + shop.getPrice("iPad");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return "";
                }, executor))
                .collect(Collectors.toList());
        futures.stream()
                .map(CompletableFuture::join);

        futures.stream()
                .map(f -> {
                    try {
                        return f.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    return "";
                })
                .forEach(System.out::println);

        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        logger.info("duration: {}", duration.toMillis());
    }
}
