package nio.buffer;

import lombok.extern.slf4j.Slf4j;

import java.nio.IntBuffer;

@Slf4j
public class UseBuffer {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(20);
        log.info("-----------------------");
        log.info("position={}", intBuffer.position());
        log.info("limit={}", intBuffer.limit());
        log.info("capacity={}", intBuffer.capacity());
    }
}
