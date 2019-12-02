package echo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext context, Object message) {
        System.out.println("Invoked " + message.toString());
        ByteBuf in = (ByteBuf) message;
        System.out.println(in.writerIndex());
        final ByteBuf out = context.alloc().buffer(in.writerIndex());

        while(in.isReadable()) {
            byte readByte = in.readByte();
            System.out.print((char) readByte);
            out.writeByte(readByte);
            System.out.flush();
        }
        context.write(out);
        context.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
        cause.printStackTrace();
        context.close();
    }
}
