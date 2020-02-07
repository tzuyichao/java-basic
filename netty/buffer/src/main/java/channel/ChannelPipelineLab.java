package channel;

import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.channel.embedded.EmbeddedChannel;

public class ChannelPipelineLab {
    public static void main(String[] args) {
        EmbeddedChannel channel = new EmbeddedChannel();
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast("handler1", new FirstHandler());
        pipeline.addFirst("handler2", new SecondHandler());

        channel.writeInbound("test");
        channel.close();
    }
}
