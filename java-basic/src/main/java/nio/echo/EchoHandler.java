package nio.echo;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

@Slf4j
public class EchoHandler implements Runnable {
    private Selector selector;
    private final SocketChannel socketChannel;
    private final SelectionKey selectionKey;
    private final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    private static final int RECEIVING = 0, SENDING = 1;
    private int state = RECEIVING;

    public EchoHandler(Selector selector, SocketChannel socketChannel) throws IOException {
        this.selector = selector;
        this.socketChannel = socketChannel;
        socketChannel.configureBlocking(false);
        selectionKey = socketChannel.register(selector, 0);
        selectionKey.attach(this);
        selectionKey.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }

    @Override
    public void run() {
        try {
            if (state == RECEIVING) {
                int length = 0;
                while((length = socketChannel.read(byteBuffer))> 0) {
                    log.info(new String(byteBuffer.array(), 0, length));
                }
                byteBuffer.flip();
                selectionKey.interestOps(SelectionKey.OP_WRITE);
                state = SENDING;
            } else if (state == SENDING) {
                socketChannel.write(byteBuffer);
                byteBuffer.clear();
                selectionKey.interestOps(SelectionKey.OP_READ);
                state = RECEIVING;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
