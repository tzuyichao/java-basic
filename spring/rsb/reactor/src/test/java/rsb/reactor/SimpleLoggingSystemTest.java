package rsb.reactor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.logging.LoggingSystem;

import static org.springframework.boot.logging.LoggingSystem.SYSTEM_PROPERTY;

@Slf4j
public class SimpleLoggingSystemTest {

    @Test
    public void basic() {
        log.info("logging system setting({}): {}", SYSTEM_PROPERTY, System.getProperty(SYSTEM_PROPERTY));
        LoggingSystem loggingSystem = LoggingSystem.get(this.getClass().getClassLoader());
        log.info("default logging System: {}", loggingSystem);
    }
}
