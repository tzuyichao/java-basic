package com.example.migrationlab;

import io.r2dbc.mssql.MssqlConnectionConfiguration;
import io.r2dbc.mssql.MssqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.util.concurrent.CountDownLatch;

public class MigrationTests {
    @Test
    void testMigration() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        MssqlConnectionConfiguration erpConfig = MssqlConnectionConfiguration.builder()
                .host("localhost")
                .username("sa")
                .password("sa")
                .database("SYNCERP")
                .build();

        MssqlConnectionConfiguration mdmConfig = MssqlConnectionConfiguration.builder()
                .host("localhost")
                .username("sa")
                .password("sa")
                .database("MDM")
                .build();

        ConnectionFactory erpConnectionFactory = new MssqlConnectionFactory(erpConfig);
        ConnectionFactory mdmConnectionFactory = new MssqlConnectionFactory(mdmConfig);

        Disposable disposable = Flux.usingWhen(
                erpConnectionFactory.create(),
                (connection -> connection.createStatement("SELECT KUNNR FROM dbo.V_ERP_CUSTOMER").execute()),
                (connection -> connection.close())
        ).flatMap(result -> result.map((row, rowMetadata) -> row.get("KUNNR")))
                .buffer(500)
                .doOnComplete(latch::countDown)
                .subscribe(batch -> {
                    Flux.usingWhen(
                            mdmConnectionFactory.create(),
                            (connection -> {
                                Publisher<Void> txn = connection.beginTransaction();
                                Flux<String> inserts = Flux.fromIterable(batch)
                                        .flatMap(kunnr -> connection.createStatement("INSERT INTO dev.MDM_MIGRATION_LAB ( KUNNR ) VALUES ( '" + kunnr + "' )")
                                                .execute())
                                        .flatMap(result -> result.getRowsUpdated())
                                        .map(count -> "Inserted " + count + " rows");
                                Publisher<Void> commit = connection.commitTransaction();
                                return Flux.concat(txn, inserts, commit);
                            }),
                            (connection -> connection.close())
                    ).subscribe();
                });
        latch.await();
        disposable.dispose();
    }
}
