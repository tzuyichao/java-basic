package nio.buffer;

import java.nio.ByteBuffer;

public class BufferTypeLab {
    public static void main(String[] args) {
        ByteBuffer directByteBuffer = ByteBuffer.allocateDirect(100);
        ByteBuffer cachedByteBuffer = ByteBuffer.allocate(100);

        BufferUtil.inspectBuffer(directByteBuffer);
        BufferUtil.inspectBuffer(cachedByteBuffer);
    }
}
