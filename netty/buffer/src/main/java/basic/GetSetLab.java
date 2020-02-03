package basic;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

@Slf4j
public class GetSetLab {
    public static void main(String[] args) {
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", StandardCharsets.UTF_8);
        log.info("getByte(0)={}", buf.getByte(0));
        log.info("(char)getByte(0)={}", (char)buf.getByte(0));
        int readerIndex = buf.readerIndex();
        int writerIndex = buf.writerIndex();
        log.info("readerIndex: {}, writerIndex: {}", readerIndex, writerIndex);
        buf.setByte(0, (byte) 'J');
        log.info("(char)getByte(0)={}", (char)buf.getByte(0));
        log.info("buf.readerIndex() == readerIndex: {}", (buf.readerIndex() == readerIndex));
        log.info("buf.writerIndex() == writerIndex: {}", (buf.writerIndex() == writerIndex));
    }
}
