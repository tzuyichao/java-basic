package nio.channel.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ReadLab1 {
    public static void main(String[] args) {
        try (FileInputStream fileInputStream = new FileInputStream(new File("/tmp/b.txt"));
             FileChannel fileChannel = fileInputStream.getChannel()) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(5);
            int readLength = fileChannel.read(byteBuffer);
            System.out.println(readLength);
            readLength = fileChannel.read(byteBuffer);
            System.out.println(readLength);
            byteBuffer.clear();
            readLength = fileChannel.read(byteBuffer);
            System.out.println(readLength);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
