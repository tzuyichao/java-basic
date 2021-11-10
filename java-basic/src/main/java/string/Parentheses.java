package string;

import java.util.HashSet;
import java.util.Set;

public class Parentheses {
    public static boolean isValid(String subject) {
        int check = 0;

        for(char c : subject.toCharArray()) {
            if(c == '(') {
                check++;
            } else {
                check--;
            }
            if(check < 0) {
                return false;
            }
        }

        return check == 0;
    }

    public static Set<String> generate(int n) {
        Set<String> result = new HashSet<>();
        generate("", n, result);
        return result;
    }

    private static void generate(String s, int n, Set<String> collector) {
        if(n == 0) {
            if(isValid(s)) {
                collector.add(s);
            }
        } else {
            generate(s + "(", n-1, collector);
            generate(s + ")", n-1, collector);
        }
    }
}
