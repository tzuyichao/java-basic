package discard0;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DiscardClientHandler extends SimpleChannelInboundHandler<Object> {
    private ByteBuf content;
    private ChannelHandlerContext ctx;
    long count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        this.ctx = ctx;

        content = ctx.alloc().directBuffer(DiscardClient.SIZE).writeZero(DiscardClient.SIZE);

        generateTraffic();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        content.release();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

    }

    private void generateTraffic() {
        log.info("count: {}", count);
        ctx.writeAndFlush(content.retainedDuplicate()).addListener(trafficGenerator);
    }

    private final ChannelFutureListener trafficGenerator = new ChannelFutureListener() {
        @Override
        public void operationComplete(ChannelFuture future) throws Exception {
            if(future.isSuccess()) {
                generateTraffic();
            } else {
                future.cause().printStackTrace();
                future.channel().close();
            }
        }
    };
}
