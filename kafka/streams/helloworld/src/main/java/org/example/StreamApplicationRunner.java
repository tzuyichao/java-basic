package org.example;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;

import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class StreamApplicationRunner {
    public static void execute(StreamsBuilder builder, Properties props) {
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
