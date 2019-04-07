package nio.buffer;

import java.nio.IntBuffer;

public class Lab1 {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.wrap(new int[] {1, 2, 3, 4, 5});
        System.out.println(intBuffer.getClass().getName());
        System.out.println("Capacity: " + intBuffer.capacity());
        System.out.println("Limit: " + intBuffer.limit());
        System.out.println("Position: " + intBuffer.position());
        System.out.println("Mark: " + intBuffer.mark());

        intBuffer.limit(3);
        for(int i=0; i<intBuffer.capacity(); i++) {
            try {
                System.out.println(i + ": " + intBuffer.get(i));
            } catch(IndexOutOfBoundsException e) {
                System.out.println("Got IndexOutOfBoundsException on i=" + i + " due to intBuffer.limit(3)");
            }
        }
    }
}
