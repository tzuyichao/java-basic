package basic;

import lombok.extern.slf4j.Slf4j;
import reactor.netty.Connection;
import reactor.netty.tcp.TcpClient;

import java.nio.charset.StandardCharsets;

@Slf4j
public class HelloClientApplication {
    public static void main(String[] args) {
        Connection connection = TcpClient.create()
                .host("localhost")
                .port(8080)
                .handle((inbound, outbound) -> {
                    log.info("invoked");
                    inbound.receive()
                            .asString(StandardCharsets.UTF_8)
                            .log()
                            .log("[Client received]")
                            .doOnNext(s -> {
                                log.info("received: {}", s);
                            })
                            .doOnComplete(() -> {
                                log.info("Completed");
                            })
                            .subscribe(s -> {
                                log.info("receive: {}", s);
                            });
                    log.info("print completed");
                    return outbound;
                })
                .connectNow();
        connection.onDispose()
                .block();
    }
}
