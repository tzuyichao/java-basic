package echo0;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

@Slf4j
public class EchoClientHandler extends ChannelInboundHandlerAdapter {
    private final ByteBuf firstMessage;

    public EchoClientHandler() {
        firstMessage = Unpooled.buffer(EchoClient.SIZE);
//        for(int i=0; i<firstMessage.capacity(); i++) {
//            firstMessage.writeByte((byte) i);
////            log.info("{}", (byte)i);
//        }
        try {
            firstMessage.writeBytes("測試".getBytes("UTF-8"));
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.info("sending: {}", firstMessage.readableBytes());
        ctx.writeAndFlush(firstMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        ctx.write(msg);
//        log.info("channelRead: {}", ((ByteBuf) msg));
        ByteBuf messageBuf = ((ByteBuf) msg);
        if(messageBuf.hasArray()) {
            byte[] messageByteArray = messageBuf.array();
            for(byte b: messageByteArray) {
                log.info("{}", (char)b);
            }
        } else {
            log.info("not array: {}, {}", messageBuf.readableBytes(), messageBuf.readCharSequence(messageBuf.readableBytes(), Charset.forName("UTF-8")));
        }
//        log.info("received: {}", ((ByteBuf) msg).readByte());
        ctx.disconnect();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
