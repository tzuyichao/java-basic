package retry;

public class App {
    public static void main(String[] args) {
        BusinessOperation<String> getCustom = new Retry<String>(new FindCustomer());
        try {
            String custom = getCustom.perform();
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }
}
