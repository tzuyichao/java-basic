package main;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;

public class ConnectionFactoryDiscovery {
    public static void main(String[] args) {
        ConnectionFactoryOptions connectionFactoryOptions = ConnectionFactoryOptions.builder()
                .option(ConnectionFactoryOptions.DRIVER, "h2")
                .option(ConnectionFactoryOptions.PROTOCOL, "mem")
                .option(ConnectionFactoryOptions.DATABASE, "testdb")
                .build();
        ConnectionFactory connectionFactory = ConnectionFactories.find(connectionFactoryOptions);
        System.out.println(connectionFactory);
        System.out.println(connectionFactory.create());
    }
}
