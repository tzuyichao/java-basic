package com.example.cloud.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GatewayDemo1MovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayDemo1MovieApplication.class, args);
	}

}
