package hello.before;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

public class DecorateMsgAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        String originMsg = (String) args[0];
        args[0] = "<<<< " + originMsg + " >>>>";
    }

    public static void main(String[] args) {
        DummyPrinter dummyPrinter = new DummyPrinter();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new DecorateMsgAdvice());
        proxyFactory.setTarget(dummyPrinter);

        DummyPrinter dummyPrinterProxy = (DummyPrinter) proxyFactory.getProxy();
        dummyPrinterProxy.write("Hello, World!");
    }
}
