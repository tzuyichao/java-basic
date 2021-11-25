package org.acme.quickstart.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Locale;

@ApplicationScoped
public class HelloService {
    @Inject
    @Named("en_US")
    Locale en_US;

    @Inject
    @Named("es_ES")
    Locale es_ES;

    public String getGreeting() {
        return "Hello";
    }

    public String getGreeting(String locale) {
        if(locale.startsWith("en")) {
            return "Hello from " + en_US.getDisplayCountry();
        }
        if(locale.startsWith("es")) {
            return "Hola desde " + es_ES.getDisplayCountry();
        }
        return "Unknown locale";
    }
}
