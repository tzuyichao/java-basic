package provider;

import api.IRpcHelloService;

public class RpcHelloServiceImpl implements IRpcHelloService {
    @Override
    public String hello(String name) {
        return "Hello, " + name;
    }
}
