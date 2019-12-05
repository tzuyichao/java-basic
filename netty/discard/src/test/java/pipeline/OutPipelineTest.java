package pipeline;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.embedded.EmbeddedChannel;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

@Slf4j
class OutPipelineTest {
    static class OutHandlerA extends ChannelOutboundHandlerAdapter {
        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            log.info("OutHandlerA invoked");
            super.write(ctx, msg, promise);
        }
    }

    static class OutHandlerB extends ChannelOutboundHandlerAdapter {
        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            log.info("OutHandlerB invoked");
            super.write(ctx, msg, promise);
        }
    }

    static class OutHandlerB2 extends ChannelOutboundHandlerAdapter {
        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            log.info("OutHandlerB2 invoked");
//            super.write(ctx, msg, promise);
//            ctx.fireChannelInactive();
        }
    }

    static class OutHandlerC extends ChannelOutboundHandlerAdapter {
        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            log.info("OutHandlerC invoked");
            super.write(ctx, msg, promise);
        }
    }

    private void execute(ChannelInitializer<EmbeddedChannel> initializer) {
        EmbeddedChannel channel = new EmbeddedChannel(initializer);
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeInt(10);

        channel.writeOutbound(byteBuf);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Done.");
    }

    @Test
    void test_outbound_pipeline() {
        ChannelInitializer<EmbeddedChannel> initializer = new ChannelInitializer<>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new OutHandlerA());
                pipeline.addLast(new OutHandlerB());
                pipeline.addLast(new OutHandlerC());
            }
        };
        execute(initializer);
    }

    @Test
    void test_cutting_pipeline() {
        ChannelInitializer<EmbeddedChannel>  initializer = new ChannelInitializer<>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new OutHandlerA());
                pipeline.addLast(new OutHandlerB2());
                pipeline.addLast(new OutHandlerC());
            }
        };
        execute(initializer);
    }
}
