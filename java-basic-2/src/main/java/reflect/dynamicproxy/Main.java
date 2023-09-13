package reflect.dynamicproxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        LogHandler logHandler = new LogHandler();
        Greeting greeting = (Greeting) logHandler.proxyOf(new GreetingService());
        logger.info("Using greeting service: {}", greeting.hello("Mike"));
    }
}
