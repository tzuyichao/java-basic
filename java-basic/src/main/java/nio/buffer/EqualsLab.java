package nio.buffer;

import java.nio.IntBuffer;

public class EqualsLab {
    static void info(IntBuffer intBuffer) {
        System.out.println("Position: " + intBuffer.position() + ", limit: " + intBuffer.limit() + ", remaining: " + intBuffer.remaining() + ", capacity: " + intBuffer.capacity());
    }

    public static void main(String[] args) {
        int[] intArray = new int[] {1, 2, 3};
        IntBuffer buffer1 = IntBuffer.allocate(10);
        buffer1.put(intArray);
        IntBuffer buffer2 = IntBuffer.allocate(5);
        buffer2.put(intArray);
        buffer1.limit(5);

        info(buffer1);
        info(buffer2);
        if(buffer1.equals(buffer2)) {
            System.out.println("Equals!!");
        }
    }
}
