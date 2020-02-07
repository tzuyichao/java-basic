package channel;

import io.netty.channel.ChannelPromise;
import io.netty.channel.embedded.EmbeddedChannel;

public class ChannelPipelineLab {
    public static void main(String[] args) {
        EmbeddedChannel channel = new EmbeddedChannel();
        channel.pipeline().addLast("handler1", new FirstHandler());
        channel.writeInbound("test");
        channel.close();
    }
}
