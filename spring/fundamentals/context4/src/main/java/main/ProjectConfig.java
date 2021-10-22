package main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {
    @Bean
    public Person person() {
        var p = new Person();
        p.setName("Ella");
        p.setParrot(parrot());
        return p;
    }

    @Bean
    public Parrot parrot() {
        var p = new Parrot();
        p.setName("Koko");
        return p;
    }
}
