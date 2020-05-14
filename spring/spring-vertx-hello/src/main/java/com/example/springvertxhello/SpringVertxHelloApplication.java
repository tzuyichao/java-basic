package com.example.springvertxhello;

import com.example.springvertxhello.vertice.ServerVertice;
import io.vertx.core.Vertx;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@Log
@SpringBootApplication
public class SpringVertxHelloApplication {
	@Autowired
	ServerVertice serverVertice;

	public static void main(String[] args) {
		SpringApplication.run(SpringVertxHelloApplication.class, args);
	}

	@PostConstruct
	public void deployVertice() {
		log.info("deploy vertices");
		final Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(serverVertice);
	}
}
