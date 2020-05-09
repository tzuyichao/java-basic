package aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class HelloAIOClient {

    public static void main(String[] args) throws Exception {
        AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
        client.connect(new InetSocketAddress("localhost", 9000), null, new CompletionHandler<Void, Object>() {
            @Override
            public void completed(Void result, Object attachment) {
                try {
                    client.write(ByteBuffer.wrap("This is test message".getBytes())).get();
                    System.out.println("message send");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println(exc);
            }
        });
        Thread.sleep(2000);
        final ByteBuffer bb = ByteBuffer.allocate(100);
        client.read(bb, null, new CompletionHandler<Integer, Object>() {
            @Override
            public void completed(Integer result, Object attachment) {
                System.out.println("complete: " + result);
                System.out.println(bb.position());
                System.out.println("response: " + new String(Arrays.copyOf(bb.array(), bb.position())));
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println(exc);
            }
        });

        Thread.sleep(Integer.MAX_VALUE);
    }
}
