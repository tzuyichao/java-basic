package nio.buffer;

import java.nio.ByteBuffer;

public class GetLab1 {
    public static void main(String[] args) {
        byte[] byteArraySource = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArraySource);
        byte[] byteArrayTarget = new byte[4];
        while(byteBuffer.hasRemaining()) {
            int readLength = Math.min(byteArrayTarget.length, byteBuffer.remaining());
            byteBuffer.get(byteArrayTarget, 0, readLength);
            for(int i = 0; i < readLength; i++) {
                System.out.print(byteArrayTarget[i] + " ");
            }
            System.out.println();
        }
    }
}
