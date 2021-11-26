package org.acme.quickstart;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Map;

public class HelloWorldQuarkusTestResourceLifecycleManager implements QuarkusTestResourceLifecycleManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldQuarkusTestResourceLifecycleManager.class);
    @Override
    public Map<String, String> start() {
        LOGGER.info("Start Test Suite execution");
        return Collections.emptyMap();
    }

    @Override
    public void stop() {
        LOGGER.info("STOP Test Suite execution");
    }

    @Override
    public void inject(Object testInstance) {
        LOGGER.info("Executing {}", testInstance.getClass().getName());
    }

    @Override
    public int order() {
        return 0;
    }
}
