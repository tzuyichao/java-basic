package decoder;

import handler.StringProcessHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class MessageToMessageDecoderTest {
    @Test
    void test_Integer2StringDecoder_happy() {
        ChannelInitializer<EmbeddedChannel> initializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(new Byte2IntegerReplayDecoder());
                ch.pipeline().addLast(new Integer2StringDecoder());
                ch.pipeline().addLast(new StringProcessHandler());
            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(initializer);
        for(int i = 0; i<10; i++) {
            ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
            buffer.writeInt(i);
            channel.writeInbound(buffer);
        }
    }
}
