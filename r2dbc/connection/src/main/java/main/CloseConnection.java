package main;

import io.r2dbc.h2.H2Connection;
import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.h2.H2ConnectionOption;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;

public class CloseConnection {
    private static final Logger log = Loggers.getLogger(CloseConnection.class);

    public static void main(String[] args) {
        H2ConnectionFactory connectionFactory = new H2ConnectionFactory(H2ConnectionConfiguration.builder()
                .inMemory("testdb")
                .property(H2ConnectionOption.DB_CLOSE_DELAY, "-1")
                .build());
        Mono<H2Connection> connection = connectionFactory.create();
        H2Connection conn = connection.block();

        log.info("testing...");
        Publisher<Void> closePublisher = conn.close();
        Mono<Void> monoClose = Mono.from(closePublisher);
        monoClose.subscribe(c -> {
            log.info("qq");
        });
        log.info("Done.");
    }
}
