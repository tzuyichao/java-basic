package brute;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * = 7
 */
public class ReverseInteger {
    public int reverse(int x) {
        try {
            String[] strs = Integer.toString(x).split("");
            List<String> list = Stream.of(strs)
                    .filter(str -> !"-".equals(str))
                    .collect(Collectors.toList());
            Collections.reverse(list);
            if ("-".equals(strs[0])) {
                return Integer.parseInt("-" + list.stream().collect(Collectors.joining()));
            }

            return Integer.parseInt(list.stream().collect(Collectors.joining()));
        } catch(NumberFormatException e) {
            return 0;
        }
    }
}
