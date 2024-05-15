package com.example.migrationlab.service;

import com.example.migrationlab.model.ErpCustomer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class DataTransferService {
    private final DatabaseClient erpDatabaseClient;
    private final DatabaseClient mdmDatabaseClient;

    public DataTransferService(DatabaseClient erpDatabaseClient, DatabaseClient mdmDatabaseClient) {
        this.erpDatabaseClient = erpDatabaseClient;
        this.mdmDatabaseClient = mdmDatabaseClient;
    }

    public Mono<Void> transferData() {
        return erpDatabaseClient.sql("SELECT KUNNR, KTOKD, NAME1, NAME2, NAME3, NAME4, SORT1, SORT2, COUNTRY, BRAN1, BRAN2, BRAN3, BRAN4, BRAN5  FROM dbo.V_ERP_CUSTOMER vec")
                .fetch()
                .all()
                .doOnNext(row -> log.info("Fetched row: {}", row))
                .map(row -> ErpCustomer.builder()
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
                        .build())
                .flatMap(erpCustomer -> mdmDatabaseClient.sql("INSERT INTO dev.MDM_MIGRATION_LAB (KUNNR, KTOKD, NAME1, NAME2, NAME3, NAME4, SORT1, SORT2, COUNTRY, BRAN1, BRAN2, BRAN3, BRAN4, BRAN5) VALUES (:kunnr, :ktokd, :name1, :name2, :name3, :name4, :sort1, :sort2, :country, :bran1, :bran2, :bran3, :bran4, :bran5)")
                        .bind("kunnr", erpCustomer.getKunnr())
                        .bind("ktokd", erpCustomer.getKtokd())
                        .bind("name1", erpCustomer.getName1())
                        .bind("name2", erpCustomer.getName2())
                        .bind("name3", erpCustomer.getName3())
                        .bind("name4", erpCustomer.getName4())
                        .bind("sort1", erpCustomer.getSort1())
                        .bind("sort2", erpCustomer.getSort2())
                        .bind("country", erpCustomer.getCountry())
                        .bind("bran1", erpCustomer.getBran1())
                        .bind("bran2", erpCustomer.getBran2())
                        .bind("bran3", erpCustomer.getBran3())
                        .bind("bran4", erpCustomer.getBran4())
                        .bind("bran5", erpCustomer.getBran5())
                        .fetch()
                        .rowsUpdated()
                        .doOnNext(count -> log.info("Inserted {} rows", count))
                )
                .then()
                .doOnError(throwable -> log.error("Error transferring data", throwable))
                .doOnTerminate(() -> log.info("Data transfer complete"));
    }
}
