package stream;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class StreamIntsLab {
    public static void main(String[] args) {
        IntStream rndIntStream = new Random().ints(20, 48, 126);
        String result = rndIntStream
                .peek(n -> log.info("n: {}", n))
                .mapToObj(n -> String.valueOf((char) n))
                .collect(Collectors.joining());
        System.out.println(result);

        // default randomNumberOrigin = Integer.MAX_VALUE, randomNumberBound = 0
        // => RandomIntsSpliterator => rng.internalNextInt(origin, bound)
        // => 最終會用Random#nextInt()
        IntStream defaultRandomIntStream = new Random().ints();
        defaultRandomIntStream
                .limit(20)
                .forEach(n -> log.info("n: {}", n));
    }
}
