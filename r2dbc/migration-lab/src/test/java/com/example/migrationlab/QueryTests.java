package com.example.migrationlab;

import io.r2dbc.mssql.MssqlConnectionConfiguration;
import io.r2dbc.mssql.MssqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class QueryTests {
    @Test
    void testQuery() {
        Scheduler scheduler = Schedulers.newBoundedElastic(10, 100, "testQuery");
        MssqlConnectionConfiguration config = MssqlConnectionConfiguration.builder()
                .host("localhost")
                .username("sa")
                .password("password")
                .database("MDM")
                .build();

        ConnectionFactory connectionFactory = new MssqlConnectionFactory(config);

        Flux<String> customerFlux = Flux.usingWhen(
                connectionFactory.create(),
                connection -> connection.createStatement("SELECT top 100 * FROM dbo.MDM_CUSTOMER_MASTER").execute(),
                connection -> connection.close()
        )
                .flatMap(result -> result.map((row, rowMetadata) -> row.get("mdm_id") + " " + rowMetadata.getColumnMetadata("mdm_id").getName() + " " + rowMetadata.getColumnMetadata("mdm_id").getNullability()))
                .subscribeOn(scheduler);

        customerFlux
                .doOnNext(System.out::println)
                .doOnError(error -> System.out.println("Error: " + error.getMessage()))
                .blockLast();
    }
}
