package com.example.basic2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.basic2.mapper")
public class Basic2Application {

	public static void main(String[] args) {
		SpringApplication.run(Basic2Application.class, args);
	}

}
