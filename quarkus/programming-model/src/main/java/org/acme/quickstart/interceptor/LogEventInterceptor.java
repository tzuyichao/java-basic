package org.acme.quickstart.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@LogEvent
@Interceptor
public class LogEventInterceptor {
    private static Logger LOGGER = LoggerFactory.getLogger(LogEventInterceptor.class);
    static List<Event> events = new ArrayList<>();

    @AroundInvoke
    public Object logEvent(InvocationContext ctx) throws Exception {
        events.add(new Event(ctx.getMethod().getName(), Arrays.deepToString(ctx.getParameters())));
        LOGGER.info("Current Events: {}", events);
        return ctx.proceed();
    }
}
