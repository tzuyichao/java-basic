package handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InboundHandlerDemoTest {
    @Test
    @DisplayName("InboundHandler Life Cycle Test")
    public void lifecycle() {
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

        ChannelFuture channelFuture = channel.close();
        channelFuture.addListener((f) -> {
            System.out.println(f.toString());
        });
    }
}
