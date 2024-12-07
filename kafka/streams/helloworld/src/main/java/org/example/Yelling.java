package org.example;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

import java.util.Properties;

public class Yelling {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "yelling-app");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9093");
        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> simpleFirstStream =
                builder.stream("test.topic.v0", Consumed.with(Serdes.String(), Serdes.String()));
        KStream<String, String> upperCaseStream = simpleFirstStream.mapValues(value -> value.toUpperCase());
        upperCaseStream.to("test.topic.v1", Produced.with(Serdes.String(), Serdes.String()));

        StreamApplicationRunner.execute(builder, props);
    }
}
