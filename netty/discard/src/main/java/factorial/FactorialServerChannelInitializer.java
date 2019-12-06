package factorial;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslContext;

public class FactorialServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    private final SslContext sslContext;

    public FactorialServerChannelInitializer(SslContext sslContext) {
        this.sslContext = sslContext;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        if(sslContext != null) {
            pipeline.addLast(sslContext.newHandler(ch.alloc()));
        }



        pipeline.addLast(new FactorialServerHandler());
    }
}
