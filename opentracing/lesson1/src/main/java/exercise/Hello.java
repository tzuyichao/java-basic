package exercise;

import io.jaegertracing.internal.JaegerTracer;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;
import lib.Tracing;

public final class Hello {
    private final Tracer tracer;

    private Hello(Tracer tracer) {
        this.tracer = tracer;
    }

    private void sayHello(String helloTo) {
        Span span = tracer.buildSpan("say-hello").start();
        String helloStr = String.format("Hello, %s!", helloTo);
        System.out.println(helloStr);
        span.log("testing");
        span.finish();
    }

    private void test() {
        try(Scope scope = tracer.buildSpan("test").startActive(true)) {
            System.out.println("Testing called!");
            scope.span().log("Test method");
        }
    }

    public static void main(String[] args) {
        if(args.length != 1) {
            throw new IllegalArgumentException("Expecting one argument");
        }
        String helloTo = args[0];
        //new Hello(GlobalTracer.get()).sayHello(helloTo);
        try(JaegerTracer tracer = Tracing.init("hello-world")) {
            Hello hello = new Hello(tracer);
            hello.sayHello(helloTo);
            hello.test();
        }
    }
}
