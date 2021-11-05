package com.example.hello;

import io.r2dbc.spi.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

import java.time.Duration;
import java.util.Arrays;

@SpringBootApplication
public class HelloApplication {
	private static final Logger log = LoggerFactory.getLogger(HelloApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HelloApplication.class, args);
	}

	@Bean
	ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
		ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();

		initializer.setConnectionFactory(connectionFactory);
		initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));

		return initializer;
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return args -> {
			repository.saveAll(Arrays.asList(
					new Customer("Chloe", "O'Brian"),
					new Customer("Kim", "Bauer"),
					new Customer("David", "Palmer"),
					new Customer("Michelle", "Dessler")
			)).blockLast(Duration.ofSeconds(10));
			log.info("Customers found with findAll():");
			repository.findAll()
					.doOnNext(customer -> {
						log.info(customer.toString());
					}).blockLast(Duration.ofSeconds(10));

			repository.findById(1L)
					.doOnNext(customer -> {
						log.info("Customer found with findById(1L):");
						log.info(customer.toString());
						log.info("");
					}).block(Duration.ofSeconds(10));
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("----------------------------");
			repository.findByLastName("Bauer")
					.doOnNext(customer -> {
						log.info(customer.toString());
					}).blockLast(Duration.ofSeconds(10));
		};
	}
}
