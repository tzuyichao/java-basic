package nio.buffer;

import java.nio.CharBuffer;

public class TestLimit {
    public static void main(String[] args) {
        char[] charArray = new char[] {'a', 'b', 'c', 'd', 'e'};
        CharBuffer charBuffer = CharBuffer.wrap(charArray);
        BufferUtil.inspectBuffer(charBuffer);
        charBuffer.limit(3);
        System.out.println(charBuffer);
        charBuffer.put(0, 'o');
        charBuffer.put(1, 'p');
        charBuffer.put(2, 'q');
        charBuffer.put(3, 'r');  // get java.lang.IndexOutOfBoundsException
        charBuffer.put(4, 's');
    }
}
