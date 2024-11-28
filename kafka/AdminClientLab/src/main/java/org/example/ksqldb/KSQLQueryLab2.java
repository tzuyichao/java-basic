package org.example.ksqldb;

import io.confluent.ksql.api.client.Client;
import io.confluent.ksql.api.client.ClientOptions;
import io.confluent.ksql.api.client.SourceDescription;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.concurrent.ExecutionException;

public class KSQLQueryLab2 {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        ClientOptions options = ClientOptions.create()
                .setHost(dotenv.get("KSQLDB_SERVER"))
                .setPort(Integer.parseInt(dotenv.get("KSQLDB_PORT")));
        try(Client client = Client.create(options);) {
            SourceDescription description = client.describeSource("QUALITY_QUALITYALERT_PRODUCTLINEUNBLOCK_V0").get();
            System.out.println("This source is a " + description.type());
            System.out.println("This stream/table has " + description.fields().size() + " columns.");
            System.out.println(description.writeQueries().size() + " queries write to this stream/table.");
            System.out.println(description.readQueries().size() + " queries read from this stream/table.");
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
