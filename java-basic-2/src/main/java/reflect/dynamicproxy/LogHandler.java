package reflect.dynamicproxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LogHandler implements InvocationHandler {
    private static final Logger logger = LoggerFactory.getLogger(LogHandler.class);
    private Object delegate;

    public Object proxyOf(Object delegate) {
        this.delegate = delegate;
        return Proxy.newProxyInstance(delegate.getClass().getClassLoader(),
                delegate.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;

        try {
            logger.info("before enter {} with args {}", method.getName(), args);
            result = method.invoke(delegate, args);
            logger.info("end of method {}", method.getName());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return result;
    }
}
