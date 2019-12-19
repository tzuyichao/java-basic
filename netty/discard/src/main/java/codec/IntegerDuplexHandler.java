package codec;

import decoder.Byte2IntegerDecoder;
import encoder.Integer2ByteEncoder;
import io.netty.channel.CombinedChannelDuplexHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IntegerDuplexHandler extends CombinedChannelDuplexHandler<Byte2IntegerDecoder, Integer2ByteEncoder> {
    public IntegerDuplexHandler() {
        super(new Byte2IntegerDecoder(), new Integer2ByteEncoder());
    }
}
