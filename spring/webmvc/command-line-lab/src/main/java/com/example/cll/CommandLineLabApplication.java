package com.example.cll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommandLineLabApplication {

	public static void main(String[] args) {
		if(args.length > 0) {
			SpringApplication app = new SpringApplication(CommandLineLabApplication.class);
			app.setWebApplicationType(WebApplicationType.NONE);
			app.run(args);
		} else {
			SpringApplication.run(CommandLineLabApplication.class, args);
		}
	}

}
