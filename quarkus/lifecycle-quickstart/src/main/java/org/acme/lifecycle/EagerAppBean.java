package org.acme.lifecycle;

import io.quarkus.runtime.Startup;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

@Startup
@ApplicationScoped
public class EagerAppBean {
    private static final Logger LOGGER = Logger.getLogger(EagerAppBean.class);
    private final String name;

    public EagerAppBean(NameGenerator generator) {
        this.name = generator.createName();
        LOGGER.info("name: " + name);
    }
}
