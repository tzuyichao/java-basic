package bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class BufferTypeTest {
    @Test
    void test_heap_buffer() throws UnsupportedEncodingException {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.heapBuffer();
        buffer.writeBytes("test test".getBytes());
        log.info("{}", buffer);
        if(buffer.hasArray()) {
            byte[] values = buffer.array();
            int offset = buffer.arrayOffset() + buffer.readerIndex();
            int length = buffer.readableBytes();
            log.info("offset: {}, length: {}", offset, length);
            log.info("{}", new String(values, offset, length, StandardCharsets.UTF_8));
        } else {
            log.info("not heap buffer");
        }
        buffer.release();
    }

    @Test
    void test_direct_buffer() {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        byte[] data = "test 測試 test".getBytes();
        log.info("data: {}", ByteBufUtil.hexDump(data));
        log.info("str: {}", new String(data, 0, data.length, StandardCharsets.UTF_8));
        buffer.writeBytes(data);
        log.info("\n{}", ByteBufUtil.prettyHexDump(buffer));
        log.info("{}", buffer);
        if(buffer.hasArray()) {
            byte[] values = buffer.array();
            int offset = buffer.arrayOffset() + buffer.readerIndex();
            int length = buffer.readableBytes();
            log.info("offset: {}, length: {}", offset, length);
            log.info("{}", new String(values, offset, length, StandardCharsets.UTF_8));
        } else {
            log.info("not heap buffer");
            int length = buffer.readableBytes();
            byte[] values = new byte[length];
            buffer.getBytes(buffer.readerIndex(), values);
            log.info(ByteBufUtil.hexDump(buffer));
            log.info("{}", new String(values, StandardCharsets.UTF_8));
        }
        buffer.release();
    }
}
