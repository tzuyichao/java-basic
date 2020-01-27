package memory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FalseSharing implements Runnable {
    public final static int NUM_THREADS = 4;
    public final static long ITERATTIONS = 500L * 1000L * 1000L;
    private final int arrayIndex;
    private static VolatileLong[] longs = new VolatileLong[NUM_THREADS];        // 51456280668
    //private static VolatileLong2[] longs = new VolatileLong2[NUM_THREADS];    // 4105762514

    static {
        for(int i=0; i<longs.length; i++) {
            longs[i] = new VolatileLong();
            //longs[i] = new VolatileLong2();
        }
    }

    public FalseSharing(int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.nanoTime();
        runTest();
        log.info("duration: {}", (System.nanoTime() - start));
    }

    private static void runTest() throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];
        for(int i=0; i<threads.length; i++) {
            threads[i] = new Thread(new FalseSharing(i));
        }
        for(Thread t: threads) {
            t.start();
        }
        for(Thread t: threads) {
            t.join();
        }
    }

    private static void runTest2() throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];
        for(int i=0; i<threads.length; i++) {
            threads[i] = new Thread(new FalseSharing(i));
        }
        for(Thread t: threads) {
            t.start();
        }
        for(Thread t: threads) {
            t.join();
        }
    }

    @Override
    public void run() {
        long i = ITERATTIONS + 1;
        while(0 != --i) {
            longs[arrayIndex].value = i;
        }
    }

    public static final class VolatileLong {
        public volatile long value = 0L;
    }

    public static final class VolatileLong2 {
        volatile long p0, p1, p2, p3, p4, p5, p6;
        public volatile long value = 0L;
        volatile long q0, q1, q2, q3, q4, q5, q6;
    }

//    @jdk.internal.vm.annotation.Contended
//    static final class VolatileLong3 {
//        public volatile long value = 0L;
//    }
}
