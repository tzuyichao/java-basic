package reflect.proxy.dummymock;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MockInvocationHandler implements InvocationHandler {
    private Map<String, Long> executionStore = new HashMap<>();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().equals("hashCode")) {
            System.out.println("hashCode called");
            return proxy.getClass().hashCode();
        } else {
            executionStore.compute(method.getName(), (methodName, old) -> null == old ? 1L : old + 1);
        }
        return null;
    }

    public Optional<Long> executionCount(String name) {
        if(executionStore.containsKey(name)) {
            return Optional.of(executionStore.get(name));
        } else {
            return Optional.empty();
        }
    }
}
