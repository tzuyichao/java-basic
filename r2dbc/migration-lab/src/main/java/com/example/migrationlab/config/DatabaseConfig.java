package com.example.migrationlab.config;

import io.r2dbc.mssql.MssqlConnectionConfiguration;
import io.r2dbc.mssql.MssqlConnectionFactory;
import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.core.DatabaseClient;

import java.time.Duration;

@Configuration
public class DatabaseConfig {
    @Bean
    public ConnectionFactory erpConnectionFactory() {
        MssqlConnectionConfiguration config = MssqlConnectionConfiguration.builder()
                .host("sa.delta.corp")
                .username("sa")
                .password("sa")
                .database("SYNCERP")
                .build();
        ConnectionPoolConfiguration poolConfig = ConnectionPoolConfiguration.builder(new MssqlConnectionFactory(config))
                .maxIdleTime(Duration.ofMinutes(30))
                .initialSize(10)
                .maxSize(20)
                .build();
        return new ConnectionPool(poolConfig);
    }
    @Bean
    public DatabaseClient erpDatabaseClient(ConnectionFactory erpConnectionFactory) {
        return DatabaseClient.create(erpConnectionFactory);
    }

    @Bean
    public ConnectionFactory mdmConnectionFactory() {
        MssqlConnectionConfiguration config = MssqlConnectionConfiguration.builder()
                .host("sa.delta.corp")
                .username("sa")
                .password("sa")
                .database("MDM")
                .build();
        ConnectionPoolConfiguration poolConfig = ConnectionPoolConfiguration.builder(new MssqlConnectionFactory(config))
                .maxIdleTime(Duration.ofMinutes(30))
                .initialSize(10)
                .maxSize(20)
                .build();
        return new ConnectionPool(poolConfig);
    }

    @Bean
    public DatabaseClient mdmDatabaseClient(ConnectionFactory mdmConnectionFactory) {
        return DatabaseClient.create(mdmConnectionFactory);
    }
}
