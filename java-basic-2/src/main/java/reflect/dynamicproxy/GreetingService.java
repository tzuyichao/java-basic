package reflect.dynamicproxy;

import org.h2.util.StringUtils;

public class GreetingService implements Greeting {

    @Override
    public String hello(String name) {
        if(StringUtils.isNullOrEmpty(name)) {
            return "Hello world!";
        } else {
            return "Hi! " + name;
        }
    }
}
