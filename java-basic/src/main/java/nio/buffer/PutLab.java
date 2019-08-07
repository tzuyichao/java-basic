package nio.buffer;

import java.io.UnsupportedEncodingException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class PutLab {
    public static void main(String[] args) {
        byte[] byteArray = new byte[] {1, 2, 3, 4, 5};
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        byteBuffer.position(8);
        try {
            byteBuffer.put(byteArray);
        } catch(BufferOverflowException e) {
            e.printStackTrace();
        }
        byteBuffer.clear();
        byteBuffer.putChar('中');
        byteBuffer.putChar('a');
        byte[] bytes = byteBuffer.array();
        for(int i=0; i<bytes.length; i++) {
            System.out.print(Integer.toHexString(bytes[i]) + " ");
        }
        System.out.println();

        System.out.println(Charset.defaultCharset().name());

        byteArray = "這裡是地球".getBytes();
        byteBuffer = ByteBuffer.wrap(byteArray);
        System.out.println("byteBuffer: " + byteBuffer.getClass().getName());
        CharBuffer charBuffer = byteBuffer.asCharBuffer();
        System.out.println("charBuffer: " + charBuffer.getClass().getName());
        for(int i=0; i<charBuffer.capacity(); i++) {
            System.out.print(charBuffer.get(i) + " ");
        }
        System.out.println();

        try {
            byteArray = "這裡是地球".getBytes("UTF-16BE");
            byteBuffer = ByteBuffer.wrap(byteArray);
            System.out.println("byteBuffer: " + byteBuffer.getClass().getName());
            charBuffer = byteBuffer.asCharBuffer();

            System.out.println("charBuffer: " + charBuffer.getClass().getName());
            for(int i=0; i<charBuffer.capacity(); i++) {
                System.out.print(charBuffer.get(i) + " ");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
