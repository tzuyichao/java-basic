package nio.channel.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class WriteLab2 {
    public static void main(String[] args) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File("/tmp/a.txt"));
             FileChannel fileChannel = fileOutputStream.getChannel()) {
            for (int i = 0; i < 10; i++) {
                Thread thread1 = new Thread(() -> {
                    try {
                        ByteBuffer byteBuffer = ByteBuffer.wrap("abcdef\r\n".getBytes());
                        fileChannel.write(byteBuffer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                Thread thread2 = new Thread(() -> {
                    try {
                        ByteBuffer byteBuffer = ByteBuffer.wrap("日本航空\r\n".getBytes());
                        fileChannel.write(byteBuffer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                thread1.start();
                thread2.start();
            }
            Thread.sleep(3000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
