package org.greenrivers.spring.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class WebServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebServletApplication.class, args);
	}

}
