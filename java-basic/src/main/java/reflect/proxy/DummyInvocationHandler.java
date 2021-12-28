package reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DummyInvocationHandler implements InvocationHandler {
    private List<String> target = new ArrayList<>();

    public DummyInvocationHandler() {
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("DummyInvocationHandler called:");
        Object returnValue = method.invoke(target, args);
        System.out.println(returnValue);
        return returnValue;
    }
}
