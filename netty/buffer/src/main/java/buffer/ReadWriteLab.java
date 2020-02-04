package buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

@Slf4j
public class ReadWriteLab {
    public static void main(String[] args) {
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", StandardCharsets.UTF_8);
        log.info("{}", buf);
        log.info("readByte(): {}", (char) buf.readByte());
        log.info("{}", buf);
        int readerIndex = buf.readerIndex();
        int writerIndex = buf.writerIndex();
        buf.writeByte((byte)'?');
        log.info("{}", buf);
        log.info("readerIndex() == readerIndex: {}", (buf.readerIndex() == readerIndex));
        log.info("writerIndex() != writerIndex: {}", (buf.writerIndex() != writerIndex));
    }
}
