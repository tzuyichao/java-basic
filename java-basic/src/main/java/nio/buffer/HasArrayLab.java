package nio.buffer;

import java.nio.ByteBuffer;

public class HasArrayLab {
    private static void dumpInfo(String name, ByteBuffer buffer) {
        System.out.println(name + " has array? " + buffer.hasArray());
        System.out.println(name + " class: " + buffer.getClass().toGenericString());
    }

    public static void main(String[] args) {
        ByteBuffer buffer1 = ByteBuffer.allocate(100);
        ByteBuffer buffer2 = ByteBuffer.allocateDirect(100);
        dumpInfo("buffer1", buffer1);
        dumpInfo("buffer2", buffer2);
    }
}
