package reflect.dynamicproxy;

import com.microsoft.sqlserver.jdbc.StringUtils;

public class GreetingService implements Greeting {

    @Override
    public String hello(String name) {
        if(StringUtils.isEmpty(name)) {
            return "Hello world!";
        } else {
            return "Hi! " + name;
        }
    }
}
