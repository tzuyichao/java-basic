package brute;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 * = 1704
 */
public class StringHalvesAlike {
    private static final boolean[] target = new boolean[256];

    static {
        target['a'] = true;
        target['e'] = true;
        target['i'] = true;
        target['o'] = true;
        target['u'] = true;
    }

    private int countTarget(String s) {
        int count = 0;
        for(byte b: s.toLowerCase(Locale.ROOT).getBytes(StandardCharsets.UTF_8)) {
            if(target[b]) {
                count+=1;
            }
        }
        return count;
    }

    private boolean solve1(String s) {
        String left = s.substring(0, s.length()/2);
        String right = s.substring(s.length()/2);

        int countLeft = countTarget(left);
        int countRight = countTarget(right);
        return countLeft == countRight;
    }

    public boolean halvesAreAlike(String s) {
        return solve1(s);
    }
}
