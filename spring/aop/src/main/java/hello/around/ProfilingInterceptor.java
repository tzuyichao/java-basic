package hello.around;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

public class ProfilingInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object retVal = invocation.proceed();
        stopWatch.stop();
        dumpInfo(invocation, stopWatch.getTotalTimeMillis());
        return retVal;
    }

    private void dumpInfo(MethodInvocation invocation, long ms) {
        System.out.println(invocation.getMethod().getName() + " took:" + ms);
    }
}
