package channel;

import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.channel.embedded.EmbeddedChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChannelPipelineLab {
    public static void main(String[] args) {
        final String handler1_name = "handler1";
        EmbeddedChannel channel = new EmbeddedChannel();
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(handler1_name, new FirstHandler());
        pipeline.addFirst("handler2", new SecondHandler());
        pipeline.addLast("handler3", new ThirdHandler());

        channel.writeInbound("test");

        log.info("remove {}", handler1_name);
        pipeline.remove(handler1_name);
        channel.writeInbound("test2");

        channel.close();
    }
}
