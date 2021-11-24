package org.acme.quickstart.service;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HelloService {
    public String getGreeting() {
        return "Hello";
    }
}
