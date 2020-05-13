package consumer;

import api.IRpcHelloService;
import api.IRpcService;
import lombok.extern.java.Log;
import proxy.RpcProxy;

@Log
public class RpcConsumer {
    public static void main(String[] args) {
        IRpcHelloService rpcHelloService = RpcProxy.create(IRpcHelloService.class);
        log.info(rpcHelloService.hello("Tom老師"));

        IRpcService iRpcService = RpcProxy.create(IRpcService.class);
        log.info("8 + 2 = " + iRpcService.add(8, 2));
    }
}
