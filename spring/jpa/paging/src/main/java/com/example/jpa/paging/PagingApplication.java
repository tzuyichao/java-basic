package com.example.jpa.paging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class PagingApplication {

	public static void main(String[] args) {
		SpringApplication.run(PagingApplication.class, args);
	}

}
