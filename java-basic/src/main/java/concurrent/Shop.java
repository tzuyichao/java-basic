package concurrent;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop {
    private String name;
    private Random random;

    public Shop(String name) {
        this.name = name;
        random = new Random();
    }

    private static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sync method for get product's price
     * @param product
     * @return
     */
    public double getPrice(final String product) throws ProductNotAvailableException {
        return calculatePrice(product);
    }

    private double calculatePrice(final String product) throws ProductNotAvailableException {
        delay();
        if("iPhone XR".equalsIgnoreCase(product)) {
            throw new ProductNotAvailableException("Too Expensive");
        }
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    /**
     * Async method for get product's price
     * @param product
     * @return
     */
    public Future<Double> getPriceAsync(final String product) {
        CompletableFuture<Double> priceFutute = new CompletableFuture<>();
        new Thread(()-> {
            try {
                double price = calculatePrice(product);
                priceFutute.complete(price);
            } catch(ProductNotAvailableException e) {
                priceFutute.completeExceptionally(e);
            }
        }).start();
        return priceFutute;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
