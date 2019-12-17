package decoder;

import handler.StringProcessHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

import static decoder.StringReplayDecoderTest.randInMod;

@Slf4j
public class BuiltinDecoderTest {
    public static final String SPLITTER = "\r\n";
    public static final String SPLITTER_TAB = "\t";
    public static final String CONTENT = " 中文測試 test ";

    @Test
    void test_LineBasedFrameDecoder() throws NoSuchAlgorithmException {
        ChannelInitializer<EmbeddedChannel> initializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
                ch.pipeline().addLast(new StringDecoder(StandardCharsets.UTF_8));
                ch.pipeline().addLast(new StringProcessHandler());
            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(initializer);
        for(int i=0; i<100; i++) {
            int random = randInMod(3);
            ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
            for(int j=0; j<random; j++) {
                buffer.writeBytes(CONTENT.getBytes(StandardCharsets.UTF_8));
            }
            buffer.writeBytes(SPLITTER.getBytes(StandardCharsets.UTF_8));
            channel.writeInbound(buffer);
        }
    }

    @Test
    void test_DelimiterBasedFrameDecoder() throws NoSuchAlgorithmException {
        final ByteBuf delimiter = ByteBufAllocator.DEFAULT.buffer();
        delimiter.writeBytes(SPLITTER_TAB.getBytes(StandardCharsets.UTF_8));
        ChannelInitializer<EmbeddedChannel> initializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(new DelimiterBasedFrameDecoder(10240, true, delimiter));
                ch.pipeline().addLast(new StringDecoder(StandardCharsets.UTF_8));
                ch.pipeline().addLast(new StringProcessHandler());
            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(initializer);
        for(int i=0; i<100; i++) {
            int random = randInMod(3);
            log.info("random: {}", random);
            ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
            for(int j=0; j<random; j++) {
                buffer.writeBytes(CONTENT.getBytes(StandardCharsets.UTF_8));
            }
            buffer.writeBytes(SPLITTER_TAB.getBytes(StandardCharsets.UTF_8));
            channel.writeInbound(buffer);
        }
    }

    @Test
    void test_LengthFieldBasedFrameDecoder() throws NoSuchAlgorithmException {
        ChannelInitializer<EmbeddedChannel> initializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(20480, 0, 4, 0, 4));
                ch.pipeline().addLast(new StringDecoder(StandardCharsets.UTF_8));
                ch.pipeline().addLast(new StringProcessHandler());
            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(initializer);
        for(int i=0; i<100; i++) {
            int random = randInMod(3);
            ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
            buffer.writeInt(CONTENT.getBytes(StandardCharsets.UTF_8).length * random);
            for(int j=0; j<random; j++) {
                buffer.writeBytes(CONTENT.getBytes(StandardCharsets.UTF_8));
            }
            channel.writeInbound(buffer);
        }
    }

    /**
     * ----------------------------------------
     * | length | version | content           |
     * ----------------------------------------
     *   4 byte   2 byte    n bytes
     */
    @Test
    void test_LengthFieldBasedFrameDecoder2() {
        final int VERSION = 100;
        ChannelInitializer<EmbeddedChannel> initializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024, 0, 4, 2, 6));
                ch.pipeline().addLast(new StringDecoder(StandardCharsets.UTF_8));
                ch.pipeline().addLast(new StringProcessHandler());
            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(initializer);
        for(int i=0; i<100; i++) {
            ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
            log.info("第 {} 次發送", i);
            String msg = i + CONTENT;
            byte[] data = msg.getBytes(StandardCharsets.UTF_8);
            buffer.writeInt(data.length);
            buffer.writeChar(VERSION);
            buffer.writeBytes(data);
            channel.writeInbound(buffer);
        }
    }
}
