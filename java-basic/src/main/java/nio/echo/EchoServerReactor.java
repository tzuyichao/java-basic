package nio.echo;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

@Slf4j
public class EchoServerReactor implements Runnable {
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;

    public EchoServerReactor() throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(8088));
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        selectionKey.attach(new AcceptHandler(selector, serverSocketChannel));
        log.info("server started...");
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while(iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    dispatch(selectionKey);
                }
                selectionKeys.clear();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void dispatch(SelectionKey selectionKey) {
        Runnable handler = (Runnable) selectionKey.attachment();
        if(handler != null) {
            handler.run();
        }
    }

    public static void main(String[] args) throws IOException {
        (new Thread(new EchoServerReactor())).run();
    }
}
