package codec;

import handler.IntegerProcessHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class Byte2IntegerCodecTest {
    @Test
    void testHappy() {
        ChannelInitializer<EmbeddedChannel> initializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(new Byte2IntegerCodec());
                ch.pipeline().addLast(new IntegerProcessHandler());
            }
        };

        EmbeddedChannel channel = new EmbeddedChannel(initializer);
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        buffer.writeInt(10);
        channel.writeInbound(buffer);
        for(int i=0; i<10; i++) {
            channel.write(i);
        }
        channel.flush();
        ByteBuf buffer2 = channel.readOutbound();
        while(null != buffer2) {
            log.info("o: {}", buffer2.readInt());
            buffer2 = channel.readOutbound();
        }
    }
}
