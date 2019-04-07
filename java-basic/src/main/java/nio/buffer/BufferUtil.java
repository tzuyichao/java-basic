package nio.buffer;

import java.nio.Buffer;

public final class BufferUtil {
    private BufferUtil() {}

    public static void inspectBuffer(Buffer buffer) {
        System.out.println(String.format("buffer: %s [capacity: %d, limit: %d, position: %d, isDirect: %b]",
                buffer.getClass().getName(), buffer.capacity(), buffer.limit(), buffer.position(), buffer.isDirect()));
    }
}
