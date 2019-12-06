package bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ReadWriteTest {
    void inspect(ByteBuf buffer) {
        log.info(buffer.toString());
        log.info("is readable: {}", buffer.isReadable());
        log.info("is writable: {}", buffer.isWritable());
//        log.info("ByteBuf readerIndex: {}", buffer.readerIndex());
//        log.info("ByteBuf writerIndex: {}", buffer.writerIndex());
//        log.info("ByteBuf capacity: {}", buffer.capacity());
//        log.info("ByteBuf max capacity: {}", buffer.maxCapacity());
    }

    @Test
    void basic_operation() {
        log.info("===== initial ByteBuf =====");
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(9, 100);
        inspect(buffer);
        log.info("===== write 4 bytes =====");
        buffer.writeBytes(new byte[] {1, 2, 3, 4});
        inspect(buffer);
        log.info("===== read byte =====");
        buffer.readByte();
        inspect(buffer);
    }
}
