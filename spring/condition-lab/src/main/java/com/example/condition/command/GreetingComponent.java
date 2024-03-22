package com.example.condition.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@ShellComponent
public class GreetingComponent {
    @ShellMethod("greeting command")
    public String greeting(@ShellOption(defaultValue = "spring") String arg) {
        WebClient client = WebClient.create("http://localhost:8080");
        Mono<String> greetingMono = client.get()
                .uri("/greeting/{name}", arg)
                .retrieve()
                .bodyToMono(String.class);
        return greetingMono.block();
    }
}
