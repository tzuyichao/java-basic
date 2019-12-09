package bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.UnpooledByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ReadWriteTest {
    static void inspect(ByteBuf buffer) {
        log.info("{}", buffer);
        log.info("is readable: {}", buffer.isReadable());
        log.info("is writable: {}", buffer.isWritable());
        log.info("reference count: {}", buffer.refCnt());
    }

    @Test
    void test_basic_operation() {
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

    @Test
    void test_reference_count() {
        log.info("===== initial ByteBuf =====");
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(9, 100);
        inspect(buffer);
        log.info("===== retain =====");
        buffer.retain();
        inspect(buffer);
        log.info("===== retain =====");
        buffer.retain();
        inspect(buffer);
        log.info("===== release =====");
        buffer.release();
        inspect(buffer);
    }

    @Test
    void test_allocator() {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(9, 100);
        inspect(buffer);
        buffer = ByteBufAllocator.DEFAULT.buffer();
        inspect(buffer);
        buffer = UnpooledByteBufAllocator.DEFAULT.heapBuffer();
        inspect(buffer);
        buffer = PooledByteBufAllocator.DEFAULT.directBuffer();
        inspect(buffer);
    }
}
