package nio.buffer;

import java.nio.Buffer;
import java.nio.CharBuffer;

public class MarkLab1 {
    public static void logBuffer(Buffer buffer) {
        System.out.println(String.format("capacity: %d, position: %d, limit: %d",
                buffer.capacity(),
                buffer.position(),
                buffer.limit()));
    }

    public static void main(String[] args) {
        CharBuffer charBuffer = CharBuffer.wrap(new char[] {'a', 'b', 'c', 'd', 'e', 'f'});

        logBuffer(charBuffer);
        System.out.println("before set charBuffer position to 2");
        charBuffer.position(2);
        charBuffer.mark();

        logBuffer(charBuffer);
        System.out.println("after set charBuffer position to 2");
        charBuffer.position(3);
        System.out.println("after set charBuffer position to 3");
        logBuffer(charBuffer);
        System.out.println("before reset charBuffer position");
        charBuffer.reset();
        System.out.println("after reset charBuffer position");
        logBuffer(charBuffer);
    }
}
