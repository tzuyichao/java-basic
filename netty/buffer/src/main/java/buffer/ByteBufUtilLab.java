package buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

@Slf4j
public class ByteBufUtilLab {
    public static void main(String[] args) {
        ByteBuf buf1 = Unpooled.copiedBuffer("Netty in Action rocks!", StandardCharsets.UTF_8);
        ByteBuf buf2 = Unpooled.copiedBuffer("Netty in Action rocks!", StandardCharsets.UTF_8);
        log.info("buf1: {}", buf1);
        log.info("buf2: {}", buf2);
        log.info("equals? {}", ByteBufUtil.equals(buf1, buf2));

        log.info("hexDump: {}", ByteBufUtil.hexDump("abc".getBytes()));
    }
}
