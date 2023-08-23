package org.example;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;

import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-streams-hello-example");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "datagovstg-kfk01.deltaww.com:9093");
        props.put("security.protocol", "SASL_PLAINTEXT");
        props.put("sasl.mechanism", "SCRAM-SHA-512");
        String jaas = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"terence.chao1\" password=\"1qaz@WSX\";";
        props.put("sasl.jaas.config", jaas);

        StreamsBuilder builder = new StreamsBuilder();

        builder.stream("test.testtopic.in.v0", Consumed.with(Serdes.String(), Serdes.String()))
                .peek((key, value) -> System.out.println("Key: " + key + ", value: " + value))
                .mapValues(value -> value.toUpperCase())
                .peek((key, value) -> System.out.println("Key: " + key + ", value: " + value))
                .to("test.testtopic.out.v0", Produced.with(Serdes.String(), Serdes.String()));

        try(KafkaStreams streams = new KafkaStreams(builder.build(), props);) {
            final CountDownLatch shutdownLatch = new CountDownLatch(1);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                streams.close(Duration.ofSeconds(2));
                shutdownLatch.countDown();
            }));
            try {
                System.out.println(streams.state());
                streams.start();
                System.out.println(streams.state());
                System.out.println("streams started.");
                shutdownLatch.await();
            } catch (Throwable e) {
                System.exit(1);
            }
        }
        System.out.println("Done.");
        System.exit(0);
    }
}