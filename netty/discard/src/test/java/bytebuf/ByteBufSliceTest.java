package bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static bytebuf.ReadWriteTest.inspect;

@Slf4j
public class ByteBufSliceTest {
    @Test
    void testSlice() {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(9, 100);
        log.info("init buffer: {}", buffer);
        inspect(buffer);
        buffer.writeBytes(new byte[] {1, 2, 3, 4});
        log.info("after write bytes: {}", buffer);
        inspect(buffer);
        ByteBuf slice = buffer.slice();
        log.info("sliced buffer: {}", slice);
        inspect(slice);
    }
}
