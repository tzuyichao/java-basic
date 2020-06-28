package hello.afterreturning;

import hello.before.Guitarist;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

public class SimpleAfterReturningAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("After returning '" + method.getName() + "' put down guitar.");
    }

    public static void main(String[] args) {
        Guitarist guitarist = new Guitarist();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new SimpleAfterReturningAdvice());
        proxyFactory.setTarget(guitarist);

        Guitarist guitaristProxy = (Guitarist) proxyFactory.getProxy();
        guitaristProxy.sing();
    }
}
