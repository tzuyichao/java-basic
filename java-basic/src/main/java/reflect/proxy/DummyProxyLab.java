package reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.List;

public class DummyProxyLab {
    public static void main(String[] args) {
        InvocationHandler invocationHandler = new DummyInvocationHandler();
        List<String> listProxy = (List<String>) Proxy.newProxyInstance(
                List.class.getClassLoader(),
                new Class[]{List.class},
                invocationHandler);
        listProxy.add("a");
    }
}
