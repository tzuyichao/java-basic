package basic;

import lombok.extern.slf4j.Slf4j;
import reactor.netty.DisposableServer;
import reactor.netty.tcp.TcpServer;

@Slf4j
public class StartingServerApplication {
    public static void main(String[] args) {
        DisposableServer server = TcpServer.create()
                .bindNow();

        server.onDispose()
                .block();
    }
}
