package connection;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.java.Log;

@Log
public class Client {
    public static void main(String[] args) {
        log.info("Client starting...");
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(eventLoopGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_REUSEADDR, true);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                //
            }
        });

        int beginPort = 8000;
        int index = 0;
        int port;
        while(!Thread.interrupted()) {
            port = beginPort + index;
            try {
                ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", port);
                channelFuture.addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        if(!channelFuture.isSuccess()) {
                            log.info("Connection Failure");
                            System.exit(0);
                        }
                    }
                });
                channelFuture.get();
            } catch(Exception e) {
                e.printStackTrace();
            }
            if(port == 8100) {
                index = 0;
            } else {
                index++;
            }
        }
    }
}
