package proxy;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import lombok.extern.java.Log;
import protocol.InvokerProtocol;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Log
public class RpcProxy {
    public static <T> T create(Class<?> clazz) {
        MethodProxy proxy = new MethodProxy(clazz);
        Class<?>[] interfaces = clazz.isInterface()?new Class[]{clazz}:clazz.getInterfaces();
        T result = (T) Proxy.newProxyInstance(clazz.getClassLoader(), interfaces, proxy);
        return result;
    }

    private static class MethodProxy implements InvocationHandler {
        private Class<?> clazz;
        public MethodProxy(Class<?> clazz) {
            this.clazz = clazz;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            log.info("invoked");
            if(Object.class.equals(method.getDeclaringClass())) {
                log.info("Pass");
                return null;
            } else {
                return rpcInvoke(proxy, method, args);
            }
        }

        private Object rpcInvoke(Object proxy, Method method, Object[] args) {
            InvokerProtocol request = new InvokerProtocol();
            request.setClassName(this.clazz.getName());
            request.setMethodName(method.getName());
            request.setParams(method.getParameterTypes());
            request.setValues(args);

            final RpcProxyHandler consumerHandler = new RpcProxyHandler();

            EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
            try {
                Bootstrap bootstrap = new Bootstrap();
                bootstrap.group(eventLoopGroup);
                bootstrap.channel(NioSocketChannel.class);
                bootstrap.option(ChannelOption.TCP_NODELAY, true);
                bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                        pipeline.addLast("frameEncode", new LengthFieldPrepender(4));
                        pipeline.addLast("encode", new ObjectEncoder());
                        pipeline.addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));
                        pipeline.addLast("handler", consumerHandler);
                    }
                });
                ChannelFuture future = bootstrap.connect("localhost", 8000).sync();
                future.channel().writeAndFlush(request).sync();
                future.channel().closeFuture().sync();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return consumerHandler.getResponse();
        }
    }
}
