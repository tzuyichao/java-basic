package org.acme.lifecycle;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
public class NameGenerator {
    private AtomicInteger id = new AtomicInteger();

    public String createName() {
        return "bean-" + id.incrementAndGet();
    }
}
