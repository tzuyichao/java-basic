package decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
public class StringReplayDecoder extends ReplayingDecoder<StringReplayDecoder.Status> {
    private int length;
    private byte[] inBytes;

    public StringReplayDecoder() {
        super(Status.PARSE_1);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        switch (state()) {
            case PARSE_1:
                length = in.readInt();
                inBytes = new byte[length];
                checkpoint(Status.PARSE_2);
                log.info("PARSE_1: length: {}", length);
                break;
            case PARSE_2:
                in.readBytes(inBytes);
                out.add(new String(inBytes, StandardCharsets.UTF_8));
                checkpoint(Status.PARSE_1);
                log.info("PARSE_2: read string: {}", new String(inBytes, StandardCharsets.UTF_8));
                break;
            default:
                break;
        }
    }

    enum Status {
        PARSE_1,
        PARSE_2
    }

}
