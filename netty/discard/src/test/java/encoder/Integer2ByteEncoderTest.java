package encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class Integer2ByteEncoderTest {

    @Test
    void testHappy() {
        ChannelInitializer<EmbeddedChannel> initializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(new Integer2ByteEncoder());
            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(initializer);
        for(int i=0; i<100; i++) {
            channel.write(i);
        }
        channel.flush();
        ByteBuf buffer = channel.readOutbound();
        while(null != buffer) {
            log.info("o: {}", buffer.readInt());
            buffer = channel.readOutbound();
        }
    }
}
