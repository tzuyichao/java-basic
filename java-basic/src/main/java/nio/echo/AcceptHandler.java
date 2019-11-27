package nio.echo;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

@Slf4j
public class AcceptHandler implements Runnable {
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    public AcceptHandler(Selector selector, ServerSocketChannel serverSocketChannel) {
        this.selector = selector;
        this.serverSocketChannel = serverSocketChannel;
    }

    @Override
    public void run() {
        //log.info("connection accepted");
        try {
            SocketChannel socketChannel = serverSocketChannel.accept();
            if(socketChannel != null) {
                new EchoHandler(selector, socketChannel);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
