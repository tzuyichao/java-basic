package nio.selector;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

@Slf4j
public class NioDiscardClient {
    public static void main(String[] args) throws IOException {
        try(SocketChannel socketChannel = SocketChannel.open()) {
            socketChannel.socket().connect(new InetSocketAddress("localhost", 8088));
            socketChannel.configureBlocking(false);
            ByteBuffer byteBuffer = ByteBuffer.wrap("hello".getBytes());
            socketChannel.write(byteBuffer);
            log.info("send completed.");
            ByteBuffer byteBufferIn = ByteBuffer.allocate(1024);
            int length = 0;
            while((length = socketChannel.read(byteBufferIn)) > 0) {
                System.out.println(new String(byteBufferIn.array(), 0, length));
            }
        }
    }
}
