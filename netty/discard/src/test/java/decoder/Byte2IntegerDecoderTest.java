package decoder;

import handler.IntegerProcessHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.channel.socket.SocketChannel;
import org.junit.jupiter.api.Test;

public class Byte2IntegerDecoderTest {
    @Test
    void test_Byte2IntegerDecoder() {
        ChannelInitializer<EmbeddedChannel> initializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new Byte2IntegerDecoder());
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
}
