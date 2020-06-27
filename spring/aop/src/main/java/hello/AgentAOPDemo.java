package hello;

import org.springframework.aop.framework.ProxyFactory;

public class AgentAOPDemo {
    public static void main(String[] args) {
        Agent agent = new Agent();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new AgentDecorator());
        proxyFactory.setTarget(agent);

        Agent agentProxy = (Agent) proxyFactory.getProxy();

        agent.speak();
        System.out.println("");
        agentProxy.speak();
    }
}
