package util;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.zip.CRC32;

public class CRC32Tests {
    @Test
    void test_happy() {
        String world = "world";
        byte[] worldBytes = world.getBytes(StandardCharsets.UTF_8);
        CRC32 crc32 = new CRC32();
        crc32.update("hello".getBytes(StandardCharsets.UTF_8));
        crc32.update(worldBytes, 0, worldBytes.length);
        long crcValue = crc32.getValue();
        System.out.println(crcValue); // 4192936109
    }

    @Test
    void test_happy2() {
        CRC32 crc32 = new CRC32();
        crc32.update("helloworld".getBytes(StandardCharsets.UTF_8));
        long crcValue = crc32.getValue();
        System.out.println(crcValue); // 4192936109
    }
}
