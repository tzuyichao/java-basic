package main;

import transformer.MyClassTransformer;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

public class AgentMain {
    public static void agentmain(String agentArgs, Instrumentation inst) throws UnmodifiableClassException {
        System.out.println("Agent Running...");
        inst.addTransformer(new MyClassTransformer(), true);
        Class[] classes = inst.getAllLoadedClasses();
        for(Class clz : classes) {
            //System.out.println(clz.getName());
            if(clz.getName().equals("com.example.client.controller.HelloController")) {
                System.out.println("Reloading: " + clz.getName());
                inst.retransformClasses(clz);
                break;
            }
        }
    }
}
