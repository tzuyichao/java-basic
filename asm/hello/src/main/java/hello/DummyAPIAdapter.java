package hello;

public class DummyAPIAdapter {
    public APIResponse createTerm(String term) {
        System.out.println("Requesting remote service...");
        try {
            Thread.sleep((long)(Math.random()*1000));
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return new APIResponse("Success");
    }
}
