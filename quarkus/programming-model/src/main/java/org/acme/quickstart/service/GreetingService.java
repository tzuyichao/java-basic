package org.acme.quickstart.service;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.constraints.Min;

@ApplicationScoped
public class GreetingService {
    public String greetingMessage(@Min(value = 16) int age) {
        if(age < 19) {
            return "Hey boys and girls";
        } else {
            return "Hey ladies and gentlemen";
        }
    }

    public String message() {
        return "Hi!";
    }
}
