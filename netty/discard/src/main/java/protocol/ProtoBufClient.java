package protocol;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

import static protocol.ProtoBufServer.PORT;

@Slf4j
public final class ProtoBufClient {
    public void runClient() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group);
            b.channel(NioSocketChannel.class);
            b.remoteAddress("localhost", PORT);
            b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                    ch.pipeline().addLast(new ProtobufEncoder());
                }
            });
            ChannelFuture f = b.connect();
            f.sync();
            Channel channel = f.channel();
            for(int i=0; i<10; i++) {
                MsgProtos.Msg message = buildMsg(i, "訊息 (" + i + ")");
                channel.writeAndFlush(message);
                log.info("sending: {}", message);
            }
            channel.flush();
            log.info("Done.");
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    private MsgProtos.Msg buildMsg(int id, String message) {
        MsgProtos.Msg.Builder builder = MsgProtos.Msg.newBuilder();
        builder.setId(id);
        builder.setContent(message);
        MsgProtos.Msg msg = builder.build();
        return msg;
    }

    public static void main(String[] args) {
        log.info("System Default Charset: {}", Charset.defaultCharset());
        new ProtoBufClient().runClient();
    }
}
