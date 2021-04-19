package com.example.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class GatewayDemo1EurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayDemo1EurekaApplication.class, args);
	}

}
