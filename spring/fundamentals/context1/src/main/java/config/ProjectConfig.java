package config;

import beans.Computer;
import beans.Dog;
import beans.Parrot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ProjectConfig {
    @Bean
    Parrot parrot() {
        var p = new Parrot();
        p.setName("Koko");
        return p;
    }

    @Bean(name = "lucky")
    Dog dog1() {
        var d = new Dog();
        d.setName("lucky");
        return d;
    }

    @Bean(value = "miki")
    Dog dog2() {
        var d = new Dog();
        d.setName("miki");
        return d;
    }

    @Bean(name = "mini")
    Dog dog3() {
        var d = new Dog();
        d.setName("mini");
        return d;
    }

    @Bean
    Computer computer1() {
        var c = new Computer();
        c.setName("Mac Book Pro");
        return c;
    }

    @Bean
    @Primary
    Computer computer2() {
        var c = new Computer();
        c.setName("Mac Book Air");
        return c;
    }
}
