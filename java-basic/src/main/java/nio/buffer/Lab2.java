package nio.buffer;

import java.nio.ByteBuffer;

import static nio.buffer.MarkLab1.logBuffer;

public class Lab2 {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[] {1, 2, 3, 4});
        BufferUtil.inspectBuffer(byteBuffer);
        for(int i=0; i<byteBuffer.capacity(); i++) {
            byteBuffer.get();
            BufferUtil.inspectBuffer(byteBuffer);
        }
    }
}
