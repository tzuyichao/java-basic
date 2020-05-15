package bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import lombok.extern.java.Log;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalUnit;

/**
 * Tom's book
 */
@Log
public class PoolLab {
    private static void pooledAllocate() {
        final byte[] CONTENT = new byte[1024];
        int loop = 1800000;
        Instant start = Instant.now();
        ByteBuf poolByteBuf = null;
        for(int i=0; i<loop; i++) {
            poolByteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(1024);
            poolByteBuf.writeBytes(CONTENT);
            poolByteBuf.release();
        }
        Instant end = Instant.now();
        log.info("Pooled ByteBuf Allocator: " + Duration.between(start, end).getNano() + " ns.");
    }

    private static void unpooledAllocate() {
        final byte[] CONTENT = new byte[1024];
        int loop = 1800000;
        Instant start = Instant.now();
        ByteBuf unpoolByteBuf = null;
        for(int i=0; i<loop; i++) {
            unpoolByteBuf = Unpooled.directBuffer(1024);
            unpoolByteBuf.writeBytes(CONTENT);
            unpoolByteBuf.release();
        }
        Instant end = Instant.now();
        log.info("Unpooled ByteBuf Allocator: " + Duration.between(start, end).getNano() + " ns.");
    }

    public static void main(String[] args) {
        pooledAllocate();
        unpooledAllocate();
    }
}
