package decoder;

import handler.IntegerProcessHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.junit.jupiter.api.Test;

public class Byte2IntegerDecoderTest {
    private void test_decoder(ByteToMessageDecoder decoder) {
        ChannelInitializer<EmbeddedChannel> initializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(decoder);
                pipeline.addLast(new IntegerProcessHandler());
            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(initializer);
        for(int idx = 0; idx < 100; idx++) {
            ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
            buf.writeInt(idx);
            channel.writeInbound(buf);
        }
    }

    @Test
    void test_Byte2IntegerDecoder() {
        test_decoder(new Byte2IntegerDecoder());
    }

    @Test
    void test_Byte2IntegerReplay() {
        test_decoder(new Byte2IntegerReplayDecoder());
    }

    @Test
    void test_IntegerAddDecoder() {
        ChannelInitializer<EmbeddedChannel> initializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new IntegerAddDecoder());
                pipeline.addLast(new IntegerProcessHandler());
            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(initializer);
        for(int idx = 0; idx < 101; idx++) {
            ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
            buf.writeInt(idx);
            channel.writeInbound(buf);
        }
    }
}
