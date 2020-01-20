package udp;

import io.vertx.core.Vertx;
import io.vertx.core.datagram.DatagramSocket;
import io.vertx.core.datagram.DatagramSocketOptions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ListenUDP {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        DatagramSocket socket = vertx.createDatagramSocket(new DatagramSocketOptions());
        socket.listen(1234, "127.0.0.1", asyncResult -> {
            if(asyncResult.succeeded()) {
                log.info("Listening ...");
                socket.handler(packet -> {
                    log.info("handling {}", packet.data());
                    log.info("sender: {}", packet.sender());
                });
            } else {
                log.error("Listen Failed {}", asyncResult.cause());
            }
        });
    }
}
