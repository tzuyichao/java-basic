package hello.before;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

public class SimpleBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("Before '" + method.getName() + "', tune guitar.");
    }

    public static void main(String[] args) {
        Guitarist johnMayer = new Guitarist();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new SimpleBeforeAdvice());
        proxyFactory.setTarget(johnMayer);

        Guitarist johnMayerProxy = (Guitarist) proxyFactory.getProxy();
        johnMayerProxy.sing();
    }
}
