package concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerLab {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println(atomicInteger.get());
        System.out.println(atomicInteger.incrementAndGet());
    }
}
