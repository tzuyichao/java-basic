package echo1;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

@Slf4j
@ChannelHandler.Sharable
public final class NettyEchoServerHandler extends ChannelInboundHandlerAdapter {
    public static final NettyEchoServerHandler INSTANCE = new NettyEchoServerHandler();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buffer = (ByteBuf) msg;
        log.info("buffer: {}", buffer);
        int length = buffer.readableBytes();
        byte[] arr = new byte[length];
        buffer.getBytes(0, arr);
        log.info("server received: {}", new String(arr, StandardCharsets.UTF_8));
        log.info("before write back: msg refCnt: {}", ((ByteBuf)msg).refCnt());

        ChannelFuture f = ctx.write(msg);
        f.addListener((ChannelFuture futureListener) -> {
            log.info("after write back: msg refCnt: {}", ((ByteBuf)msg).refCnt());
        });
    }
}