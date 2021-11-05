package main;

import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.h2.H2ConnectionMetadata;
import io.r2dbc.h2.H2ConnectionOption;
import io.r2dbc.spi.ConnectionFactoryMetadata;

public class MetadataApp {
    public static void main(String[] args) {
        H2ConnectionFactory connectionFactory = new H2ConnectionFactory(H2ConnectionConfiguration.builder()
                .inMemory("testdb")
                .property(H2ConnectionOption.DB_CLOSE_DELAY, "-1")
                .build());
        ConnectionFactoryMetadata connectionFactoryMetadata = connectionFactory.getMetadata();
        System.out.println(connectionFactoryMetadata.getName());
        System.out.println(connectionFactoryMetadata.toString());

        connectionFactory.create().subscribe(h2Connection -> {
            H2ConnectionMetadata connectionMetadata = h2Connection.getMetadata();
            System.out.println(connectionMetadata);
            System.out.println(connectionMetadata.getDatabaseProductName());
            System.out.println(connectionMetadata.getDatabaseVersion());
        });
    }
}
