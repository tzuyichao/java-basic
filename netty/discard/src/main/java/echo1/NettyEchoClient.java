package echo1;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public final class NettyEchoClient {
    private String serverIp;
    private int serverPort;
    Bootstrap b = new Bootstrap();

    public NettyEchoClient(String serverIp, int serverPort) {
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
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter message:");
            while(scanner.hasNext()) {
                String next = scanner.next();
                byte[] msgByte = next.getBytes();
                ByteBuf buffer = channel.alloc().buffer();
                buffer.writeBytes(msgByte);
                channel.writeAndFlush(buffer);
                System.out.println("Please enter message:");
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new NettyEchoClient("127.0.0.1", 8089).runClient();
    }
}
