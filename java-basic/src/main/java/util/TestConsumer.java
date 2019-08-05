package util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

public class TestConsumer {
    public static void main(String[] args) {
        Random random = ThreadLocalRandom.current();
        for(int i=0; i<10; i++) {
            System.out.println(random.nextInt(100));
        }
    }
}
