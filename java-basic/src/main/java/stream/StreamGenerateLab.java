package stream;

import lombok.extern.slf4j.Slf4j;

import java.security.SecureRandom;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class StreamGenerateLab {
    private static String randomPassword() {
        log.info("called...");
        var chars = "abcd0123!ABCD@#$";

        return new SecureRandom().ints(8, 0, chars.length())
                .mapToObj(i -> String.valueOf(chars.charAt(i)))
                .collect(Collectors.joining());
    }

    public static void main(String[] args) {
        Supplier<String> passwordSupplier = StreamGenerateLab::randomPassword;

        Stream<String> passwordStream = Stream.generate(passwordSupplier);

        List<String> result = passwordStream
                .peek(p -> log.info("Got: {}", p))
                .limit(10)
                .collect(Collectors.toList());

        System.out.println(result);

        passwordStream = Stream.generate(passwordSupplier);

        result = passwordStream
                .parallel()
                .peek(p -> log.info("Got: {}", p))
                .limit(10)
                .collect(Collectors.toList());

        System.out.println(result);
    }
}
