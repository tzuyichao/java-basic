package reflect.proxy.dummymock;

import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Mock {
    private static Map<Object, MockInvocationHandler> store = new ConcurrentHashMap<>();

    public static <T> T createMock(Class<T> clazz) {
        MockInvocationHandler proxy = new MockInvocationHandler();
        T mock = (T) Proxy.newProxyInstance(Mock.class.getClassLoader(), new Class[]{clazz}, proxy);
        store.put(mock, proxy);
        return mock;
    }

    public static <T> long count(T mock, String methodName) {
        if(store.containsKey(mock)) {
            return store.get(mock).executionCount(methodName).orElseThrow();
        } else {
            throw new IllegalArgumentException("mock object not found");
        }
    }
}
