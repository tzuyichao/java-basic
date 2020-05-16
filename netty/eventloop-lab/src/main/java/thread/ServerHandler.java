package thread;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.internal.ThreadLocalRandom;

@ChannelHandler.Sharable
public class ServerHandler extends SimpleChannelInboundHandler<ByteBuf> {
    public static final ChannelHandler INSTANCE = new ServerHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf msg) throws Exception {
        ByteBuf data = Unpooled.directBuffer();
        data.writeBytes(msg);
        Object result = getResult(data);
        channelHandlerContext.channel().writeAndFlush(result);
    }

    protected Object getResult(ByteBuf data) {
        int level = ThreadLocalRandom.current().nextInt(1, 1000);
        int time;
        if(level <= 900) {
            time = 1;
        } else if(level <= 950) {
            time = 10;
        } else if(level <= 990) {
            time = 100;
        } else {
            time = 1000;
        }
        try {
            Thread.sleep(time);
        } catch(InterruptedException e) {
        }
        return data;
    }
}
