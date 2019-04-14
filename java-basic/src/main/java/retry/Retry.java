package retry;

import java.util.concurrent.CompletableFuture;

public class Retry<T> implements BusinessOperation<T> {
    private int retryCount = 3;
    private BusinessOperation<T> businessOperation;

    public Retry(BusinessOperation<T> businessOperation) {
        this.businessOperation = businessOperation;
    }

    @Override
    public T perform() throws BusinessException {
        int currentRetry = 0;

        for(;;) {
            try {
                System.out.println("retry: " + currentRetry);
                CompletableFuture<T> future = CompletableFuture.supplyAsync(() -> {
                    try {
                        return businessOperation.perform();
                    } catch (BusinessException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                });
                return future.get();
            } catch(Exception e) {
                currentRetry++;
                if(currentRetry > retryCount) {
                    throw new BusinessException(e.getMessage());
                }
            }
        }
    }
}
