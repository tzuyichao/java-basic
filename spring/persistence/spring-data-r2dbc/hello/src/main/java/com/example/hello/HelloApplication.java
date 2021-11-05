package com.example.hello;

import io.r2dbc.spi.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Arrays;

@SpringBootApplication
public class HelloApplication {
	private static final Logger log = LoggerFactory.getLogger(HelloApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HelloApplication.class, args);
	}

	@Bean
	R2dbcEntityTemplate r2dbcEntityTemplate(ConnectionFactory connectionFactory) {
		return new R2dbcEntityTemplate(connectionFactory);
	}

	@Bean
	ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
		ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();

		initializer.setConnectionFactory(connectionFactory);
		initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));

		return initializer;
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository, R2dbcEntityTemplate template) {
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
			log.info("");
			log.info("R2DBCEntityTemplate: {}, Database Client: {}", template, template.getDatabaseClient().toString());
			template.insert(Person.class)
					.using(new Person("joe", "Joe", 34))
					.as(StepVerifier::create)
					.expectNextCount(1)
					.verifyComplete();
			template.select(Person.class)
					.first()
					.doOnNext(person -> {
						log.info(person.toString());
					})
					.as(StepVerifier::create)
					.expectNextCount(1)
					.verifyComplete();
		};
	}
}
