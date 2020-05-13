package thread;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPooledServerHandler extends ServerHandler {
    public static final ChannelHandler INSTANCE = new ThreadPooledServerHandler();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(1000);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf msg) throws Exception {
        final ByteBuf data = Unpooled.directBuffer();
        data.writeBytes(msg);
        threadPool.submit(() -> {
            Object result = getResult(data);
            channelHandlerContext.channel().writeAndFlush(result);
        });
    }
}
