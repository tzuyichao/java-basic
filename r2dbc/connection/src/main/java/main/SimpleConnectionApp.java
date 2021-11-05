package main;

import io.r2dbc.h2.H2Connection;
import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.h2.H2ConnectionOption;
import io.r2dbc.spi.ConnectionFactories;
import reactor.core.publisher.Mono;

public class SimpleConnectionApp {
    public static void main(String[] args) {
        H2ConnectionFactory connectionFactory = new H2ConnectionFactory(H2ConnectionConfiguration.builder()
                .inMemory("testdb")
                .property(H2ConnectionOption.DB_CLOSE_DELAY, "-1")
                .build());
        Mono<H2Connection> connection = connectionFactory.create();
        System.out.println(connection);
        connection.subscribe(conn -> {
            System.out.println(conn);
            System.out.println(Thread.currentThread().getName());
        });
    }
}
