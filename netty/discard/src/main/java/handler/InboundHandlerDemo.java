package handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InboundHandlerDemo extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.info("invoked: channelRegistered");
        super.channelRegistered(ctx);
    }

    public static void main(String[] args) {
        final ChannelInboundHandler channelInboundHandler = new InboundHandlerDemo();
        ChannelInitializer channelInitializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(channelInboundHandler);
            }
        };

        EmbeddedChannel channel = new EmbeddedChannel(channelInitializer);

        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeInt(10);

        channel.writeInbound(byteBuf);
        channel.flush();

        channel.writeInbound(byteBuf);
        channel.flush();

        channel.close();
    }
}
