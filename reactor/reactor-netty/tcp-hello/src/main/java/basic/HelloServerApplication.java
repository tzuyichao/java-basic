package basic;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.tcp.TcpServer;

@Slf4j
public class HelloServerApplication {
    public static void main(String[] args) {
        DisposableServer server = TcpServer.create()
                .host("localhost")
                .port(8080)
                .handle((inbound, outbound) -> {
                    log.info("write hello");
                    return outbound.sendString(Mono.just("hello, world!"));
                })
                .bindNow();
        log.info("Server Running");
        server.onDispose()
                .block();
    }
}
