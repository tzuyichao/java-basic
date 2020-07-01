package hello.except;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

public class SimpleThrowsAdvice implements ThrowsAdvice {
    public void afterThrowing(Exception e) {
        System.out.println("*******");
        System.out.println(e.getMessage());
        System.out.println("*******");
    }

    public void afterThrowing(Method method, Object[] args, Object target, Exception e) {
        System.out.println("*******");
        System.out.println(method.getName());
        System.out.println(e.getMessage());
        System.out.println("*******");
    }

    public void afterThrowing(Method method, Object[] args, Object target, RuntimeException e) {
        System.out.println("******* RuntimeException");
        System.out.println(method.getName());
        System.out.println(e.getMessage());
        System.out.println("*******");
    }

    public static void main(String[] args) {
        ErrorBean errorBean = new ErrorBean();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new SimpleThrowsAdvice());
        proxyFactory.setTarget(errorBean);

        ErrorBean errorBeanProxy = (ErrorBean) proxyFactory.getProxy();

        try {
            errorBeanProxy.errorProneMethod();
        } catch(Exception e) {
        }

        try {
            errorBeanProxy.otherErrorProneMethod();
        } catch(Exception e) {
        }
    }
}
