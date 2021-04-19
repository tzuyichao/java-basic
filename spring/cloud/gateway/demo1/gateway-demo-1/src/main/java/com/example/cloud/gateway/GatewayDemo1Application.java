package com.example.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GatewayDemo1Application {

	public static void main(String[] args) {
		SpringApplication.run(GatewayDemo1Application.class, args);
	}

}
