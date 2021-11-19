package nio.framework;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

@Slf4j
public abstract class Server {
    private Selector selector;

    protected Server(int port) throws IOException {
        selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", port));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        log.info("Service Running on {}, Waiting connection request", port);
    }

    protected void closeChannel(SelectionKey key) throws IOException {
        key.channel().close();
        key.cancel();
        log.info("Session close, closing channel...");
    }

    public void service() {
        while(true) {
            try {
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectedKeys.iterator();
                while(iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if(key.isAcceptable()) {
                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        if(socketChannel != null) {
                            log.info("Accepted...{}", socketChannel);
                            socketChannel.configureBlocking(false);
                            registerOnConnected(socketChannel, key.selector());
                        }
                    } else {
                        session(key);
                    }
                }
            } catch(IOException e) {
                log.error("Exception: {}", e);
            }
        }
    }

    abstract protected void session(SelectionKey key) throws IOException;

    abstract protected void registerOnConnected(SocketChannel channel, Selector selector) throws IOException;
}
