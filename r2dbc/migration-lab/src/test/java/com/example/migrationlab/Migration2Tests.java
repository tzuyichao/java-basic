package com.example.migrationlab;

import com.example.migrationlab.model.ErpCustomer;
import com.example.migrationlab.model.MdmCustomer;
import io.r2dbc.mssql.MssqlConnectionConfiguration;
import io.r2dbc.mssql.MssqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.Statement;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

public class Migration2Tests {
    private ConnectionFactory createSourceConnectionFactory() {
        MssqlConnectionConfiguration config = MssqlConnectionConfiguration.builder()
                .host("sa1.delta.corp")
                .username("sa1")
                .password("sa1")
                .database("MDM")
                .build();
        return new MssqlConnectionFactory(config);
    }

    private ConnectionFactory createMdmConnectionFactory() {
        MssqlConnectionConfiguration mdmConfig = MssqlConnectionConfiguration.builder()
                .host("sa.delta.corp")
                .username("sa")
                .password("sa")
                .database("MDM")
                .build();
        return new MssqlConnectionFactory(mdmConfig);
    }


    @Test
    void testMigration_using_buffer() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(720);

        ConnectionFactory sourceConnectionFactory = createSourceConnectionFactory();
        ConnectionFactory mdmConnectionFactory = createMdmConnectionFactory();

        Disposable disposable = Flux.usingWhen(
                sourceConnectionFactory.create(),
                (connection -> connection.createStatement("SELECT crm_customer_id, crm_customer_code, erp_customer_code, dgc_customer_code, customer_name, customer_hierarchy_node_id, erp_industry_code1, erp_industry_code2, erp_industry_code3, erp_industry_code4, erp_industry_code5, erp_account_group, erp_name1, erp_name2, erp_name3, erp_name4, erp_search_term, modified_date, country, trading_partner, crm_statecode, mdm_id  FROM dbo.MDM_CUSTOMER_MASTER").execute()),
                (connection -> connection.close())
        ).flatMap(result -> result.map((row, rowMetadata) -> MdmCustomer.builder()
                        .mdmId((String) row.get("mdm_id"))
                        .crmCustomerId((UUID) row.get("crm_customer_id"))
                        .crmCustomerCode((String) row.get("crm_customer_code"))
                        .erpCustomerCode((String) row.get("erp_customer_code"))
                        .dgcCustomerCode((String) row.get("dgc_customer_code"))
                        .customerName((String) row.get("customer_name"))
                        .customerHierarchyNodeId((String) row.get("customer_hierarchy_node_id"))
                        .erpIndustryCode1((String) row.get("erp_industry_code1"))
                        .erpIndustryCode2((String) row.get("erp_industry_code2"))
                        .erpIndustryCode3((String) row.get("erp_industry_code3"))
                        .erpIndustryCode4((String) row.get("erp_industry_code4"))
                        .erpIndustryCode5((String) row.get("erp_industry_code5"))
                        .erpAccountGroup((String) row.get("erp_account_group"))
                        .erpName1((String) row.get("erp_name1"))
                        .erpName2((String) row.get("erp_name2"))
                        .erpName3((String) row.get("erp_name3"))
                        .erpName4((String) row.get("erp_name4"))
                        .erpSearchTerm((String) row.get("erp_search_term"))
                        .modifiedDate((LocalDateTime) row.get("modified_date"))
                        .country((String) row.get("country"))
                        .tradingPartner((String) row.get("trading_partner"))
                        .crmStatecode((int) row.get("crm_statecode"))
                        .build()))
                .buffer(500)
                .subscribe(batch -> {
                    Flux.usingWhen(
                            mdmConnectionFactory.create(),
                            (connection -> {
                                Publisher<Void> txn = connection.beginTransaction();
                                Flux<String> inserts = Flux.fromIterable(batch)
                                        .flatMap(record -> {
                                                Statement stmt = connection.createStatement("INSERT INTO MDM.dev.MDM_MIGRATION_LAB2 (crm_customer_id, crm_customer_code, erp_customer_code, dgc_customer_code, customer_name, customer_hierarchy_node_id, erp_industry_code1, erp_industry_code2, erp_industry_code3, erp_industry_code4, erp_industry_code5, erp_account_group, erp_name1, erp_name2, erp_name3, erp_name4, erp_search_term, modified_date, country, trading_partner, crm_statecode, mdm_id) VALUES(@crmCustomerId, @crmCustomerCode, @erpCustomerCode, @dgcCustomerCode, @customerName, @customerHierarchyNodeId, @erpIndustryCode1, @erpIndustryCode2, @erpIndustryCode3, @erpIndustryCode4, @erpIndustryCode5, @erpAccountGroup, @erpName1, @erpName2, @erpName3, @erpName4, @erpSearchTerm, @modifiedDate, @country, @tradingPartner, @crmStatecode, @mdmId)");
                                                if(record.getCrmCustomerId() == null) {
                                                    stmt.bindNull("crmCustomerId", UUID.class);
                                                } else {
                                                    stmt.bind("crmCustomerId", record.getCrmCustomerId());
                                                }
                                                if(record.getCrmCustomerCode() == null) {
                                                    stmt.bindNull("crmCustomerCode", String.class);
                                                } else {
                                                    stmt.bind("crmCustomerCode", record.getCrmCustomerCode());
                                                }
                                                if(record.getErpCustomerCode() == null) {
                                                   stmt.bindNull("erpCustomerCode", String.class);
                                                } else {
                                                    stmt.bind("erpCustomerCode", record.getErpCustomerCode());
                                                }
                                                if(record.getDgcCustomerCode() == null) {
                                                    stmt.bindNull("dgcCustomerCode", String.class);
                                                } else {
                                                    stmt.bind("dgcCustomerCode", record.getDgcCustomerCode());
                                                }
                                                if(record.getCustomerName() == null) {
                                                    stmt.bindNull("customerName", String.class);
                                                } else {
                                                    stmt.bind("customerName", record.getCustomerName());
                                                }
                                                if(record.getCustomerHierarchyNodeId() == null) {
                                                    stmt.bindNull("customerHierarchyNodeId", String.class);
                                                } else {
                                                    stmt.bind("customerHierarchyNodeId", record.getCustomerHierarchyNodeId());
                                                }
                                                if(record.getErpIndustryCode1() == null) {
                                                    stmt.bindNull("erpIndustryCode1", String.class);
                                                } else {
                                                    stmt.bind("erpIndustryCode1", record.getErpIndustryCode1());
                                                }
                                                if(record.getErpIndustryCode2() == null) {
                                                    stmt.bindNull("erpIndustryCode2", String.class);
                                                } else {
                                                    stmt.bind("erpIndustryCode2", record.getErpIndustryCode2());
                                                }
                                                if(record.getErpIndustryCode3() == null) {
                                                    stmt.bindNull("erpIndustryCode3", String.class);
                                                } else {
                                                    stmt.bind("erpIndustryCode3", record.getErpIndustryCode3());
                                                }
                                                if(record.getErpIndustryCode4() == null) {
                                                    stmt.bindNull("erpIndustryCode4", String.class);
                                                } else {
                                                    stmt.bind("erpIndustryCode4", record.getErpIndustryCode4());
                                                }
                                                if(record.getErpIndustryCode5() == null) {
                                                    stmt.bindNull("erpIndustryCode5", String.class);
                                                } else {
                                                    stmt.bind("erpIndustryCode5", record.getErpIndustryCode5());
                                                }
                                                if(record.getErpAccountGroup() == null) {
                                                    stmt.bindNull("erpAccountGroup", String.class);
                                                } else {
                                                    stmt.bind("erpAccountGroup", record.getErpAccountGroup());
                                                }
                                                if(record.getErpName1() == null) {
                                                    stmt.bindNull("erpName1", String.class);
                                                } else {
                                                    stmt.bind("erpName1", record.getErpName1());
                                                }
                                                if(record.getErpName2() == null) {
                                                    stmt.bindNull("erpName2", String.class);
                                                } else {
                                                    stmt.bind("erpName2", record.getErpName2());
                                                }
                                                if(record.getErpName3() == null) {
                                                    stmt.bindNull("erpName3", String.class);
                                                } else {
                                                    stmt.bind("erpName3", record.getErpName3());
                                                }
                                                if(record.getErpName4() == null) {
                                                    stmt.bindNull("erpName4", String.class);
                                                } else {
                                                    stmt.bind("erpName4", record.getErpName4());
                                                }
                                                if(record.getErpSearchTerm() == null) {
                                                    stmt.bindNull("erpSearchTerm", String.class);
                                                } else {
                                                    stmt.bind("erpSearchTerm", record.getErpSearchTerm());
                                                }
                                                if(record.getModifiedDate() == null) {
                                                    stmt.bindNull("modifiedDate", LocalDateTime.class);
                                                } else {
                                                    stmt.bind("modifiedDate", record.getModifiedDate());
                                                }
                                                if(record.getCountry() == null) {
                                                    stmt.bindNull("country", String.class);
                                                } else {
                                                    stmt.bind("country", record.getCountry());
                                                }
                                                if(record.getTradingPartner() == null) {
                                                    stmt.bindNull("tradingPartner", String.class);
                                                } else {
                                                    stmt.bind("tradingPartner", record.getTradingPartner());
                                                }
                                                if(record.getCrmStatecode() == 0) {
                                                    stmt.bindNull("crmStatecode", Integer.class);
                                                } else {
                                                    stmt.bind("crmStatecode", record.getCrmStatecode());
                                                }
                                                stmt.bind("mdmId", record.getMdmId());
                                                return stmt.execute();
                                        })
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
