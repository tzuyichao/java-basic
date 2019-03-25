package concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Future;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger("Lab");
    private static final String Product = "Mac Book Air";
    private static final String ErrProduct = "iPhone XR";
    public static void main(String[] args) throws Exception {
        Shop shop = new Shop("Buy More");
        Instant start = Instant.now();
        logger.info("starting get price");
        double price = shop.getPrice(Product);
        logger.info("do something else???");
        Instant end = Instant.now();
        logger.info("Price of {} is {}", Product, price);
        Duration duration = Duration.between(start, end);
        logger.info("duration: {}", duration.toMillis());

        start = Instant.now();
        logger.info("starting get price");
        Future<Double> priceFuture = shop.getPriceAsync(Product);
        logger.info("do something else");
        try {
            logger.info("Trying get price result");
            price = priceFuture.get();
            end = Instant.now();
            logger.info("Price of {} is {}", Product, price);
            duration = Duration.between(start, end);
            logger.info("duration: {}", duration.toMillis());
        } catch(Exception e) {
            logger.error(e.getMessage(), e);
        }

        priceFuture = shop.getPriceAsync(ErrProduct);
        try {
            priceFuture.get();
        } catch (Exception e) {
            if(e.getCause() instanceof ProductNotAvailableException) {
                logger.error("Expected Exception caught");
            } else {
                e.printStackTrace();
            }
        }
    }
}
