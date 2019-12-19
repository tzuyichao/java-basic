package echo1;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Slf4j
public class NettyEchoDumpSendClient {
    private String serverIp;
    private int serverPort;
    Bootstrap b = new Bootstrap();

    public NettyEchoDumpSendClient(String serverIp, int serverPort) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
    }

    public void runClient() {
        log.info("running echo client");
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            b.group(group);
            b.channel(NioSocketChannel.class);
            b.remoteAddress(this.serverIp, this.serverPort);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(NettyEchoClientHandler.INSTANCE);
                }
            });
            ChannelFuture f = b.connect();
            f.addListener((ChannelFuture futureListener) -> {
                if (futureListener.isSuccess()) {
                    log.info("EchoClient client connect success");
                } else {
                    log.error("EchoClient client connect failed");
                }
            });
            f.sync();
            Channel channel = f.channel();
            String content = "you are familiar with basic object orientatation concepts like classes and objects,";
            byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
            for(int i=0; i<1000; i++) {
                ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
                buffer.writeBytes(bytes);
                channel.write(buffer);
            }
            channel.flush();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new NettyEchoDumpSendClient("127.0.0.1", 8089).runClient();
    }
}
