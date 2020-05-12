package org.acme.lifecycle;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class ApplicationLifecycleBean {
    private static final Logger LOGGER = Logger.getLogger("ListenerBean");
    
    void onStart(@Observes StartupEvent event) {
        LOGGER.info("The Application is starting...");
    }

    void onStop(@Observes ShutdownEvent event) {
        LOGGER.info("The Application is stopping...");
    }
}
