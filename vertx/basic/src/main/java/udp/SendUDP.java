package udp;

import io.vertx.core.Vertx;
import io.vertx.core.datagram.DatagramSocket;
import io.vertx.core.datagram.DatagramSocketOptions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class SendUDP {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        DatagramSocket socket = vertx.createDatagramSocket(new DatagramSocketOptions());
        socket.send("hello there", 1234, "127.0.0.1", asyncResult -> {
            log.info("send success? {}", asyncResult.succeeded());
        });
    }
}
