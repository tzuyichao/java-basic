package retry;

public final class FindCustomer implements BusinessOperation<String> {

    @Override
    public String perform() throws BusinessException {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new BusinessException("Connection Refused");
    }
}
