package string;

import java.util.Map;
import java.util.stream.Collectors;

public class CountingDuplicateCharacters {
    public static Map<String, Long> count(String str) {
        Map<String, Long> result = str.codePoints()
                .mapToObj(c -> {
                    System.out.println(c);
                    return String.valueOf(Character.toChars(c));
                })
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        return result;
    }
}
