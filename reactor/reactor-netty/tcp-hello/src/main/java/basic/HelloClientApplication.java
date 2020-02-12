package basic;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.netty.Connection;
import reactor.netty.tcp.TcpClient;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * https://github.com/reactor/reactor-netty/issues/375
 */
@Slf4j
public class HelloClientApplication {
    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        Connection connection = TcpClient.create()
                .host("localhost")
                .port(8080)
                .handle((inbound, outbound) -> {
                    log.info("invoked");
                    Flux<String> s = inbound.receive()
                            .asString(StandardCharsets.UTF_8);
                    List<String> strList = s.collectList().block();
                    log.info("strList: {}", strList);
                            /*
                            .log("[Client received]")
                            .doOnNext(s -> {
                                log.info("received: {}", s);
                            })
                            .doOnComplete(() -> {
                                log.info("Completed");
                            })
                            .doOnError(it -> {
                                log.error("Error: {}", it);
                            })
                            .subscribe(s -> latch.countDown());*/
                    log.info("print completed");
                    latch.countDown();
                    return outbound;
                })
                .connectNow();

        latch.await(30, TimeUnit.SECONDS);

        connection.onDispose()
                .block();
    }
}
