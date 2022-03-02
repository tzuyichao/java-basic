package rsb.reactor;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactoryBean;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@Slf4j
public class SchedulersExecutorServiceDecoratorsTest {
    private final AtomicInteger methodInvocationCounts = new AtomicInteger();
    private String rsb = "rsb";

    @BeforeEach
    public void before() {
        log.info("before each");
        Schedulers.resetFactory();
        Schedulers.addExecutorServiceDecorator(this.rsb, ((scheduler, scheduledExecutorService) -> {
            return this.decorate(scheduledExecutorService);
        }));
    }

    @AfterEach
    public void after() {
        log.info("after each");
        Schedulers.resetFactory();
        Schedulers.removeExecutorServiceDecorator(this.rsb);
    }

    @Test
    public void changeDefaultDecorator() {
        Flux<Integer> integerFlux = Flux.just(1).delayElements(Duration.ofMillis(1));
        StepVerifier.create(integerFlux)
                .expectNextCount(1)
                .verifyComplete();
        assertThat(this.methodInvocationCounts.get(), is(equalTo(1)));
    }

    private ScheduledExecutorService decorate(ScheduledExecutorService executorService) {
        try {
            var pfb = new ProxyFactoryBean();
            pfb.setProxyInterfaces(new Class[]{ScheduledExecutorService.class});
            pfb.addAdvice((MethodInterceptor) methodInvocation -> {
                var methodName = methodInvocation.getMethod().getName();
                this.methodInvocationCounts.incrementAndGet();
                log.info("methodName (" + methodName + ") incrementing...");
                return methodInvocation.proceed();
            });
            pfb.setSingleton(true);
            pfb.setTarget(executorService);
            return (ScheduledExecutorService) pfb.getObject();
        } catch(Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
