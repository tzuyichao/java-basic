package org.greenrivers.reactordemo;

import org.greenrivers.reactordemo.service.NotificationConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.Environment;
import reactor.bus.EventBus;

import javax.annotation.PostConstruct;

import static reactor.bus.selector.Selectors.$;

@SpringBootApplication
public class ReactorDemoApplication {
	@Autowired
	private EventBus eventBus;

	@Autowired
	private NotificationConsumer notificationConsumer;

	@Bean
	Environment env() {
		return Environment.initializeIfEmpty().assignErrorJournal();
	}

	@Bean
	public EventBus createEventBus(Environment env) {
		return EventBus.create(env, Environment.THREAD_POOL);
	}

    @PostConstruct
	private void hook() {
    	System.out.println("Starting config EventBus");
    	eventBus.on($("notificationConsumer"), notificationConsumer);
		System.out.println("Emd config EventBus");
	}

	public static void main(String[] args) {
		SpringApplication.run(ReactorDemoApplication.class, args);
	}

}
