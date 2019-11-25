package nio.selector;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

@Slf4j
public class NioDiscardServer {
    public void startServer() throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(8088));
        log.info("server started...");
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while(selector.select() > 0) {
            Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
            while(selectedKeys.hasNext()) {
                SelectionKey selectedKey = selectedKeys.next();
                if(selectedKey.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if(selectedKey.isReadable()) {
                    try(SocketChannel socketChannel = (SocketChannel) selectedKey.channel()) {
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
            selectedKeys.remove();
        }
        serverSocketChannel.close();
    }

    public static void main(String[] args) throws IOException {
        (new NioDiscardServer()).startServer();
    }
}
