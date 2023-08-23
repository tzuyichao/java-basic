package org.example;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;

import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-streams-hello-example");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "datagovstg-kfk01.deltaww.com:9093");
        props.put("security.protocol", "SASL_PLAINTEXT");
        props.put("sasl.mechanism", "SCRAM-SHA-512");
        String jaas = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"terence.chao\" password=\"1qaz@WSX\";";
        props.put("sasl.jaas.config", jaas);

        StreamsBuilder builder = new StreamsBuilder();

        builder.stream("test.testtopic.in.v0", Consumed.with(Serdes.String(), Serdes.String()))
                .mapValues(value -> value.toUpperCase())
                .to("test.testtopic.out.v0", Produced.with(Serdes.String(), Serdes.String()));

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();
        System.out.println(streams.metrics());
        System.out.println("Done.");

        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }
}