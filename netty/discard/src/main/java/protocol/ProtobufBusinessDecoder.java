package protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProtobufBusinessDecoder extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MsgProtos.Msg message = (MsgProtos.Msg) msg;
        log.info("receive msg id: {}", message.getId());
        log.info("receive msg content: {}", message.getContent());
    }
}
