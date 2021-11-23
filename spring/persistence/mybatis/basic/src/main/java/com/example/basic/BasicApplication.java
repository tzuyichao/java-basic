package com.example.basic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.basic.mapper")
public class BasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicApplication.class, args);
	}

}
