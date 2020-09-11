package main;

import transformer.MyClassTransformer;

import java.lang.instrument.Instrumentation;

public class AgentMain {
    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new MyClassTransformer(), true);
    }
}
