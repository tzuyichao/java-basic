package nio.buffer;

import java.nio.ByteBuffer;
import java.nio.InvalidMarkException;

public class FlipLab {
    public static void main(String[] args) {
        byte[] byteArray = new byte[] {1, 2, 3};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);

        byteBuffer.position(2);
        byteBuffer.mark();

        byteBuffer.flip();
        System.out.println("byteBuffer.position: " + byteBuffer.position());
        System.out.println("byteBuffer.limit: " + byteBuffer.limit());

        try {
            byteBuffer.reset();
        } catch(InvalidMarkException e) {
            System.out.println("Invalid mark exception");
        }
    }
}
