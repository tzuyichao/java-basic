package org.acme.quickstart.service;

import io.quarkus.test.junit.QuarkusTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

@QuarkusTest
public class GreetingServiceTest {
    @Inject
    GreetingService greetingService;

    @Test
    public void testGreetingServiceForYoungers() {
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> greetingService.greetingMessage(15));
    }

    @Test
    public void testGreetingServiceForTeenagers() {
        String message = greetingService.greetingMessage(18);
        Assertions.assertThat(message).isEqualTo("Hey boys and girls");
    }

    @Test
    public void testGreetingServiceForAdult() {
        String message = greetingService.greetingMessage(22);
        Assertions.assertThat(message).isEqualTo("Hey ladies and gentlemen");
    }
}
