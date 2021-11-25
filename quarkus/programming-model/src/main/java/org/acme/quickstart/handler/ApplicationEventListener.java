package org.acme.quickstart.handler;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class ApplicationEventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationEventListener.class);

    void onStart(@Observes StartupEvent event) {
        LOGGER.info("Application starting ...");
    }

    void onStop(@Observes ShutdownEvent event) {
        LOGGER.info("Application shutting down ...");
    }
}
