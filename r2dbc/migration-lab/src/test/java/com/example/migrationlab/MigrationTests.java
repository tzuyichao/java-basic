package com.example.migrationlab;

import com.example.migrationlab.model.ErpCustomer;
import io.r2dbc.mssql.MssqlConnectionConfiguration;
import io.r2dbc.mssql.MssqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.CountDownLatch;

public class MigrationTests {
    private ConnectionFactory createErpConnectionFactory() {
        MssqlConnectionConfiguration erpConfig = MssqlConnectionConfiguration.builder()
                .host("sa.delta.corp")
                .username("sa")
                .password("sa!")
                .database("SYNCERP")
                .build();
        return new MssqlConnectionFactory(erpConfig);
    }

    private ConnectionFactory createMdmConnectionFactory() {
        MssqlConnectionConfiguration mdmConfig = MssqlConnectionConfiguration.builder()
                .host("sa.delta.corp")
                .username("sa")
                .password("@sa")
                .database("MDM")
                .build();
        return new MssqlConnectionFactory(mdmConfig);
    }


    //@Test
    void testMigration() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        ConnectionFactory erpConnectionFactory = createErpConnectionFactory();
        ConnectionFactory mdmConnectionFactory = createMdmConnectionFactory();

        Disposable disposable = Flux.usingWhen(
                erpConnectionFactory.create(),
                (connection -> connection.createStatement("SELECT KUNNR, KTOKD, NAME1, NAME2, NAME3, NAME4, SORT1, SORT2, COUNTRY, BRAN1, BRAN2, BRAN3, BRAN4, BRAN5  FROM dbo.V_ERP_CUSTOMER vec").execute()),
                (connection -> connection.close())
        ).flatMap(result -> result.map((row, rowMetadata) -> ErpCustomer.builder()
                        .kunnr((String)row.get("KUNNR"))
                        .ktokd((String)row.get("KTOKD"))
                        .name1((String)row.get("NAME1"))
                        .name2((String)row.get("NAME2"))
                        .name3((String)row.get("NAME3"))
                        .name4((String)row.get("NAME4"))
                        .country((String)row.get("COUNTRY"))
                        .sort1((String)row.get("SORT1"))
                        .sort2((String)row.get("SORT2"))
                        .bran1((String)row.get("BRAN1"))
                        .bran2((String)row.get("BRAN2"))
                        .bran3((String)row.get("BRAN3"))
                        .bran4((String)row.get("BRAN4"))
                        .bran5((String)row.get("BRAN5"))
                        .build()))
                .doOnNext(record -> {
                    Mono write = Mono.usingWhen(
                            mdmConnectionFactory.create(),
                            (connection -> Mono.fromCallable(() -> connection.createStatement("INSERT INTO dev.MDM_MIGRATION_LAB ( KUNNR, KTOKD, NAME1, NAME2, NAME3, NAME4, COUNTRY, SORT1, SORT2, BRAN1, BRAN2, BRAN3, BRAN4, BRAN5 ) VALUES (@id, @ktokd, @name1, @name2, @name3, @name4, @country, @sort1, @sort2, @bran1, @bran2, @bran3, @bran4, @bran5)")
                                    .bind("id", record.getKunnr())
                                    .bind("ktokd", record.getKtokd())
                                    .bind("name1", record.getName1())
                                    .bind("name2", record.getName2())
                                    .bind("name3", record.getName3())
                                    .bind("name4", record.getName4())
                                    .bind("country", record.getCountry())
                                    .bind("sort1", record.getSort1())
                                    .bind("sort2", record.getSort2())
                                    .bind("bran1", record.getBran1())
                                    .bind("bran2", record.getBran2())
                                    .bind("bran3", record.getBran3())
                                    .bind("bran4", record.getBran4())
                                    .bind("bran5", record.getBran5())
                                    .execute())),
                            (connection -> connection.close())
                    );
                    write.doOnSuccess(s -> System.out.println(record)).subscribe();
                })
                .doOnComplete(() -> countDownLatch.countDown()).subscribe();
        countDownLatch.await();
    }

    @Test
    void testMigration_using_buffer() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(486);

        ConnectionFactory erpConnectionFactory = createErpConnectionFactory();
        ConnectionFactory mdmConnectionFactory = createMdmConnectionFactory();

        Disposable disposable = Flux.usingWhen(
                erpConnectionFactory.create(),
                (connection -> connection.createStatement("SELECT KUNNR, KTOKD, NAME1, NAME2, NAME3, NAME4, SORT1, SORT2, COUNTRY, BRAN1, BRAN2, BRAN3, BRAN4, BRAN5  FROM dbo.V_ERP_CUSTOMER vec").execute()),
                (connection -> connection.close())
        ).flatMap(result -> result.map((row, rowMetadata) -> ErpCustomer.builder()
                        .kunnr((String)row.get("KUNNR"))
                        .ktokd((String)row.get("KTOKD"))
                        .name1((String)row.get("NAME1"))
                        .name2((String)row.get("NAME2"))
                        .name3((String)row.get("NAME3"))
                        .name4((String)row.get("NAME4"))
                        .country((String)row.get("COUNTRY"))
                        .sort1((String)row.get("SORT1"))
                        .sort2((String)row.get("SORT2"))
                        .bran1((String)row.get("BRAN1"))
                        .bran2((String)row.get("BRAN2"))
                        .bran3((String)row.get("BRAN3"))
                        .bran4((String)row.get("BRAN4"))
                        .bran5((String)row.get("BRAN5"))
                        .build()))
                .buffer(500)
                .subscribe(batch -> {
                    Flux.usingWhen(
                            mdmConnectionFactory.create(),
                            (connection -> {
                                Publisher<Void> txn = connection.beginTransaction();
                                Flux<String> inserts = Flux.fromIterable(batch)
                                        .flatMap(record ->
                                                connection.createStatement("INSERT INTO dev.MDM_MIGRATION_LAB ( KUNNR, KTOKD, NAME1, NAME2, NAME3, NAME4, COUNTRY, SORT1, SORT2, BRAN1, BRAN2, BRAN3, BRAN4, BRAN5 ) VALUES (@id, @ktokd, @name1, @name2, @name3, @name4, @country, @sort1, @sort2, @bran1, @bran2, @bran3, @bran4, @bran5)")
                                                    .bind("id", record.getKunnr())
                                                    .bind("ktokd", record.getKtokd())
                                                    .bind("name1", record.getName1())
                                                    .bind("name2", record.getName2())
                                                    .bind("name3", record.getName3())
                                                    .bind("name4", record.getName4())
                                                    .bind("country", record.getCountry())
                                                    .bind("sort1", record.getSort1())
                                                    .bind("sort2", record.getSort2())
                                                    .bind("bran1", record.getBran1())
                                                    .bind("bran2", record.getBran2())
                                                    .bind("bran3", record.getBran3())
                                                    .bind("bran4", record.getBran4())
                                                    .bind("bran5", record.getBran5())
                                                .execute())
                                        .flatMap(result -> result.getRowsUpdated())
                                        .map(count -> "Inserted " + count + " rows");
                                Publisher<Void> commit = connection.commitTransaction();
                                return Flux.concat(txn, inserts, commit);
                            }),
                            (connection -> connection.close())
                    ).doOnComplete(latch::countDown)
                            .subscribe();
                });
        latch.await();
        disposable.dispose();
    }
}
