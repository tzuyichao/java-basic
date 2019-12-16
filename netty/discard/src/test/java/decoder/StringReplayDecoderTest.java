package decoder;

import handler.StringProcessHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.fail;

public class StringReplayDecoderTest {
    private String content = "瘋狂創客圈 testing ";


    public StringReplayDecoderTest() {
    }

    static int randInMod(int bound) throws NoSuchAlgorithmException {
        final SecureRandom random = SecureRandom.getInstanceStrong();
        return random.nextInt(bound) + 1;
    }

    @Test
    void test_randInMod() throws NoSuchAlgorithmException {
        for(int i=0; i<100; i++) {
            int random = randInMod(3);
            if(random > 3 || random < 1) {
                fail("wrong");
            }
        }
    }

    @Test
    void test_happy() throws NoSuchAlgorithmException {

        ChannelInitializer<EmbeddedChannel> initializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(new StringReplayDecoder());
                ch.pipeline().addLast(new StringProcessHandler());
            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(initializer);
        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
        for(int i=0; i<100; i++) {
            int randomNum = randInMod(3);
            ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
            buffer.writeInt(bytes.length * randomNum);
            for(int j=0; j<randomNum; j++) {
                buffer.writeBytes(bytes);
            }
            channel.writeInbound(buffer);
        }
    }
}
