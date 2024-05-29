package main;

import connect.ConnectionFactoryConfig;
import io.r2dbc.spi.ConnectionFactory;
import model.ErpCustomer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class QueryExample {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        String sql = """
SELECT
  vec.KUNNR AS erp_customer_code,
  CASE
    WHEN NAME1 IS NOT NULL AND NAME2 IS NULL THEN NAME1
    WHEN NAME2 IS NOT NULL AND NAME1 IS NULL THEN NAME2
    WHEN NAME1 = NAME2 THEN NAME1 ELSE NAME1 + ' ' + NAME2
  END AS customer_name,
  vsy.HKUNNR AS customer_hierarchy_node_id,
  vec.KTOKD AS erp_account_group,
  vec.KUKLA AS customer_classification,
  vec.VBUND AS trading_partner,
  vec.COUNTRY AS country,
  vec.NAME1 AS erp_name1,
  vec.NAME2 AS erp_name2, 
  vec.NAME3 AS erp_name3,
  vec.NAME4 AS erp_name4,
  vec.SORT1 AS erp_search_term1,
  vec.SORT2 AS erp_search_term2, 
  vec.BRAN1 AS erp_industry_code1,
  vec.BRAN2 AS erp_industry_code2,
  vec.BRAN3 AS erp_industry_code3,
  vec.BRAN4 AS erp_industry_code4,
  vec.BRAN5 AS erp_industry_code5,
  vec.AEDAT AS modified_date
FROM  
  dbo.V_ERP_CUSTOMER vec
  LEFT JOIN dbo.V_SMP_YSDM45 vsy ON vec.KUNNR = vsy.CUSTOMER_KEY AND vec.KTOKD = vsy.CUSTOMER_TYPE 
order by vec.KUNNR
""";
        ConnectionFactory connectionFactory = ConnectionFactoryConfig.getErpQasConnectionFactory();
        Flux<ErpCustomer> erpCustomerFlux = Mono.from(connectionFactory.create())
                .flatMapMany(connection ->
                        Flux.from(connection.createStatement(sql).execute())
                                .flatMap(result-> result.map((row, rowMetadata) -> {
                                    ErpCustomer erpCustomer = new ErpCustomer();
                                    erpCustomer.setErpCustomerCode(row.get("erp_customer_code", String.class));
                                    erpCustomer.setCustomerName(row.get("customer_name", String.class));
                                    erpCustomer.setCustomerHierarchyNodeId(row.get("customer_hierarchy_node_id", String.class));
                                    erpCustomer.setErpAccountGroup(row.get("erp_account_group", String.class));
                                    erpCustomer.setCustomerClassification(row.get("customer_classification", String.class));
                                    erpCustomer.setTradingPartner(row.get("trading_partner", String.class));
                                    erpCustomer.setCountry(row.get("country", String.class));
                                    erpCustomer.setErpName1(row.get("erp_name1", String.class));
                                    erpCustomer.setErpName2(row.get("erp_name2", String.class));
                                    erpCustomer.setErpName3(row.get("erp_name3", String.class));
                                    erpCustomer.setErpName4(row.get("erp_name4", String.class));
                                    erpCustomer.setErpSearchTerm1(row.get("erp_search_term1", String.class));
                                    erpCustomer.setErpSearchTerm2(row.get("erp_search_term2", String.class));
                                    erpCustomer.setErpIndustryCode1(row.get("erp_industry_code1", String.class));
                                    erpCustomer.setErpIndustryCode2(row.get("erp_industry_code2", String.class));
                                    erpCustomer.setErpIndustryCode3(row.get("erp_industry_code3", String.class));
                                    erpCustomer.setErpIndustryCode4(row.get("erp_industry_code4", String.class));
                                    erpCustomer.setErpIndustryCode5(row.get("erp_industry_code5", String.class));
                                    erpCustomer.setModifiedDate(row.get("modified_date", LocalDateTime.class));
                                    return erpCustomer;
                                }))
                                .doFinally(signalType -> connection.close())
                        );
        AtomicInteger count = new AtomicInteger();
        erpCustomerFlux
                .doOnNext(erpCustomer -> count.getAndIncrement())
                .doOnNext(System.out::println)
                .doFinally(signalType -> latch.countDown())
                .subscribe();
        try {
            latch.await();
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("All operations completed. Total records: " + count.get());
    }
}
