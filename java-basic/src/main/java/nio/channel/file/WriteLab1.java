package nio.channel.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class WriteLab1 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File("/tmp/a.txt"));
        FileChannel writeChannel = fileOutputStream.getChannel();
        try {
            ByteBuffer buffer = ByteBuffer.wrap("abcde".getBytes());
            System.out.println("A writeChannel.position()=" + writeChannel.position());
            System.out.println("write() 1 return: " + writeChannel.write(buffer));
            System.out.println("buffer remaining: " + buffer.remaining());
            System.out.println("B writeChannel.position()=" + writeChannel.position());
            writeChannel.position(2);
            buffer.clear();
            System.out.println("write() 2 return: " + writeChannel.write(buffer));
            System.out.println("C writeChannel.position()=" + writeChannel.position());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writeChannel.close();
            fileOutputStream.close();
        }
    }
}
