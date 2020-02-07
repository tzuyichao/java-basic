package basic;

import lombok.extern.slf4j.Slf4j;
import reactor.netty.Connection;
import reactor.netty.tcp.TcpClient;

@Slf4j
public class HelloClientApplication {
    public static void main(String[] args) {
        Connection connection = TcpClient.create()
                .host("localhost")
                .port(8080)
                .handle((inbound, outbound) -> {
                    log.info("invoked");
                    inbound.receiveObject()
                            .log("[Client received]")
                            .ofType(String.class)
                            .doOnNext(s -> {
                                log.info("receive: {}", s);
                            })
                            .subscribe();
                    log.info("print completed");
                    return outbound;
                })
                .connectNow();
        connection.onDispose()
                .block();
    }
}
