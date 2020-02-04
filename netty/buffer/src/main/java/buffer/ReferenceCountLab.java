package buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

@Slf4j
public class ReferenceCountLab {
    public static void main(String[] args) {
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", StandardCharsets.UTF_8);
        log.info("buf rc: {}", buf.refCnt());
        ByteBuf dup_duf = buf.duplicate();
        log.info("buf rc: {}", buf.refCnt());
        log.info("dup_duf rc: {}", dup_duf.refCnt());
        ByteBuf copy_duf = buf.copy();
        log.info("buf rc: {}", buf.refCnt());
        log.info("copy_duf rc: {}", copy_duf.refCnt());
        ByteBuf retain_duf = buf.retain();
        log.info("buf rc: {}", buf.refCnt());
        log.info("retain_duf rc: {}", retain_duf.refCnt());
        retain_duf.release();
        log.info("buf rc: {}", buf.refCnt());
        log.info("retain_duf rc: {}", retain_duf.refCnt());
    }
}
