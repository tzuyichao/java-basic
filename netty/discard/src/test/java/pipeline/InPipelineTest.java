package pipeline;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.embedded.EmbeddedChannel;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

@Slf4j
public class InPipelineTest {
    static class InHandlerA extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            log.info("InHandlerA invoked");
            super.channelRead(ctx, msg);
        }
    }

    static class InHandlerB extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            log.info("InHandlerB invoked");
            super.channelRead(ctx, msg);
        }
    }

    static class InHandlerC extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            log.info("InHandlerC invoked");
            super.channelRead(ctx, msg);
        }
    }

    @Test
    public void testInbound_Pipeline_basic_operation() {
        ChannelInitializer initializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new InHandlerA());
                pipeline.addLast(new InHandlerB());
                pipeline.addLast(new InHandlerC());
            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(initializer);
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeInt(10);

        channel.writeInbound(byteBuf);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
