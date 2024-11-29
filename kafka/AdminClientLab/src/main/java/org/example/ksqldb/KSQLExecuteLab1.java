package org.example.ksqldb;

import io.confluent.ksql.api.client.Client;
import io.confluent.ksql.api.client.ClientOptions;
import io.confluent.ksql.api.client.ExecuteStatementResult;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.concurrent.ExecutionException;

public class KSQLExecuteLab1 {
    public static void main(String[] args) {
        final String sql = "CREATE STREAM QUALITY_QUALITYALERT_PRODUCTLINEBLOCK_V0_DET " +
                " WITH (value_format='JSON',kafka_topic='quality.quality-alert.product-line-block.v0.det', " +
                "PARTITIONS = 1, REPLICAS = 3) " +
                " AS SELECT `eventId`, `sourceSystem`, `eventName`, `eventTime`, `actionIds`, `issueSystem`, " +
                " `issueId`, `issueLevel`, `alertSource`, `alertId`, `workOrder`, `factory`, `bu`, `bg`, `line`, " +
                " `blockRule`, `station`, `modelNumber`, `region`, `prodPlant`, `group` " +
                " FROM QUALITY_QUALITYALERT_PRODUCTLINEBLOCK_V0 WHERE `region` = 'DET' EMIT CHANGES;";
        Dotenv dotenv = Dotenv.load();
        ClientOptions options = ClientOptions.create()
                .setHost(dotenv.get("KSQLDB_SERVER"))
                .setPort(Integer.parseInt(dotenv.get("KSQLDB_PORT")));
        try(Client client = Client.create(options);) {
            ExecuteStatementResult result = client.executeStatement(sql).get();
            System.out.println("Query ID: " + result.queryId());
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
