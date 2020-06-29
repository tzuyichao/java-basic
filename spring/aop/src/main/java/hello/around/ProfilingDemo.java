package hello.around;

import org.springframework.aop.framework.ProxyFactory;

public class ProfilingDemo {
    public static void main(String[] args) {
        WorkerBean workerBean = new WorkerBean();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new ProfilingInterceptor());
        proxyFactory.setTarget(workerBean);

        WorkerBean workerBeanProxy = (WorkerBean) proxyFactory.getProxy();
        workerBeanProxy.doSomeWork(10000000);
    }
}
