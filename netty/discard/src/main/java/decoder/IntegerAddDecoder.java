package decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class IntegerAddDecoder extends ReplayingDecoder<IntegerAddDecoder.Status> {
    private int first;
    private int second;

    public IntegerAddDecoder() {
        super(Status.PARSE_1);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        switch (state()) {
            case PARSE_1:
                first = in.readInt();
                checkpoint(Status.PARSE_2);
                log.info("first: {}, switch checkpoint to PARSE_2: {}", first, state());
                break;
            case PARSE_2:
                second = in.readInt();
                checkpoint(Status.PARSE_1);
                log.info("second: {}, switch checkpoint to PARSE_1: {}", second, state());
                Integer sum = first + second;
                out.add(sum);
                log.info("add sum goes to: {}", sum);
                break;
            default:
                break;
        }
    }

    enum Status {
        PARSE_1,
        PARSE_2;
    }
}
