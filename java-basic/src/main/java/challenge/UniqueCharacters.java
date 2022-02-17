package challenge;

import java.util.HashMap;
import java.util.Map;

/**
 * Unique characters (1)
 *
 */
public class UniqueCharacters {
    private static final Character SPACE = Character.valueOf(' ');
    public boolean test(String str) {
        int l = str.length();
        Map<Character, Boolean> store = new HashMap<>();

        for(int i=0; i<l; i++) {
            Character ch = Character.valueOf(str.charAt(i));
            if(!ch.equals(SPACE)) {
                if (store.containsKey(ch)) {
                    return false;
                } else {
                    store.put(ch, Boolean.TRUE);
                }
            }
        }

        return true;
    }
}
