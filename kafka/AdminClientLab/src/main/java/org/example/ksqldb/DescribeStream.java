package org.example.ksqldb;

import io.confluent.ksql.api.client.Client;
import io.confluent.ksql.api.client.ClientOptions;
import io.confluent.ksql.api.client.SourceDescription;
import io.confluent.ksql.api.client.StreamInfo;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class DescribeStream {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        ClientOptions options = ClientOptions.create()
                .setHost(dotenv.get("KSQLDB_SERVER"))
                .setPort(Integer.parseInt(dotenv.get("KSQLDB_PORT")));
        try(Client client = Client.create(options);) {
            List<StreamInfo> streams = client.listStreams().get();
            streams.forEach(stream -> {
                try {
                    SourceDescription sourceDescription = client.describeSource(stream.getName()).get();
                    if(!sourceDescription.readQueries().isEmpty()) {
                        System.out.println("Source Stream: " + stream.getName());
                        System.out.println("Type: " + sourceDescription.type());
                        System.out.println("Fields: " + sourceDescription.fields().size());
                        sourceDescription.fields().forEach(field -> {
                            System.out.println("\tField: " + field.name() + " Type: " + field.type());
                        });
                        System.out.println("Write queries: " + sourceDescription.writeQueries().size());
                        sourceDescription.writeQueries().forEach(query -> {
                            System.out.println("\tQuery ID: " + query.getId());
                            System.out.println("\tQuery Type: " + query.getQueryType());
                            System.out.println("\tSQL: " + query.getSql());
                            System.out.println("\tSink: " + query.getSink());
                            System.out.println("\tSink Topic: " + query.getSinkTopic());
                            System.out.println("\t=======");
                        });
                        System.out.println("Read queries: " + sourceDescription.readQueries().size());
                        sourceDescription.readQueries().forEach(query -> {
                            System.out.println("\tQuery ID: " + query.getId());
                            System.out.println("\tQuery Type: " + query.getQueryType());
                            System.out.println("\tSQL: " + query.getSql());
                            System.out.println("\tSink: " + query.getSink());
                            System.out.println("\tSink Topic: " + query.getSinkTopic());
                            System.out.println("\t=======");
                        });

                        System.out.println();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
