package protocol;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

@Slf4j
public class JsonSendClient {
    private String host;
    private int port;
    private Bootstrap b;

    JsonSendClient(String host, int port) {
        this.host = host;
        this.port = port;
        this.b = new Bootstrap();
    }

    public void runClient() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            b.group(group);
            b.channel(NioSocketChannel.class);
            b.remoteAddress(this.host, this.port);
            b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {

                }
            });
            ChannelFuture f = b.connect();
            f.addListener((ChannelFuture listener) -> {
                if (listener.isSuccess()) {
                    log.info("Client connect success");
                } else {
                    log.error("Client connect failed");
                }
            });
            f.sync();
            Channel channel = f.channel();
            for(int i=0; i<100; i++) {
                JsonMsg user = build(i, i + " -> test");
                ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
                byte[] data = user.convertToJson().getBytes();
                int length = data.length;
                log.info("length: {}", length);
                buffer.writeInt(length);
                buffer.writeBytes(data);
                channel.writeAndFlush(buffer);
                log.info("send: {}", i);
            }
            channel.flush();

            ChannelFuture closeFuture = channel.closeFuture();
            closeFuture.sync();
        } catch(Exception e) {
            log.error("Exception: {}", e);
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public JsonMsg build(int id, String content) {
        JsonMsg user = new JsonMsg();
        user.setId(id);
        user.setContent(content);
        return user;
    }

    public static void main(String[] args) {
        final int port = 8090;
        final String host = "localhost";

        new JsonSendClient(host, port).runClient();
    }
}
