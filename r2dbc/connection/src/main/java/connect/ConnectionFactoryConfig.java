package connect;

import io.r2dbc.mssql.MssqlConnectionConfiguration;
import io.r2dbc.mssql.MssqlConnectionFactory;
import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.spi.ConnectionFactory;

import java.time.Duration;

public class ConnectionFactoryConfig {
    public static ConnectionFactory getErpQasConnectionFactory() {
        MssqlConnectionConfiguration config = MssqlConnectionConfiguration.builder()
                .host("sa.delta.corp")
                .port(1433)
                .username("sa")
                .password("sa")
                .database("sa")
                .build();
        return new MssqlConnectionFactory(config);
    }

    public static ConnectionFactory getMdmQasConnectionFactory() {
        MssqlConnectionConfiguration config = MssqlConnectionConfiguration.builder()
                .host("sa.delta.corp")
                .port(1433)
                .username("sa")
                .password("sa")
                .database("sa")
                .build();
        ConnectionPoolConfiguration poolConfig = ConnectionPoolConfiguration.builder(new MssqlConnectionFactory(config))
                .maxIdleTime(Duration.ofMinutes(30))
                .initialSize(10)
                .maxSize(50)
                .build();
        return new ConnectionPool(poolConfig);
    }
}
