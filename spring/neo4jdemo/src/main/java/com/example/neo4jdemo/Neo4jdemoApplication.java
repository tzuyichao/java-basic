package com.example.neo4jdemo;

import lombok.extern.java.Log;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;

@Log
@SpringBootApplication
public class Neo4jdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(Neo4jdemoApplication.class, args);
	}
}
