package nio.selector;

import lombok.extern.slf4j.Slf4j;
import nio.framework.Server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

@Slf4j
public class NioDiscardServer2 extends Server {
    public NioDiscardServer2(int port) throws IOException {
        super(port);
    }

    @Override
    protected void session(SelectionKey key) throws IOException {
        if(key.isReadable()) {
            try(SocketChannel socketChannel = (SocketChannel) key.channel()) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int length = 0;
                while ((length = socketChannel.read(byteBuffer)) > 0) {
                    byteBuffer.flip();
                    log.info(new String(byteBuffer.array(), 0, length));
                    byteBuffer.clear();
                }
            }
        }
    }

    @Override
    protected void registerOnConnected(SocketChannel channel, Selector selector) throws IOException {
        channel.register(selector, SelectionKey.OP_READ);
    }

    public static void main(String[] args) throws IOException {
        (new NioDiscardServer2(8088)).service();
    }
}
