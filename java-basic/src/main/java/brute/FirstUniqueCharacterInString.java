package brute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * = 387
 */
public class FirstUniqueCharacterInString {
    static class PosInfo {
        public final List<Integer> info = new ArrayList<>();
    }
    public int firstUniqChar(String s) {
        if(s.length() == 1) {
            return 0;
        }
        Map<String, PosInfo> pos = new HashMap<>();
        String[] tokens = s.split("");
        for(int i=0; i<tokens.length; i++) {
            if(pos.containsKey(tokens[i])) {
                pos.get(tokens[i]).info.add(i);
            } else {
                PosInfo posInfo = new PosInfo();
                posInfo.info.add(i);
                pos.put(tokens[i], posInfo);
            }
        }
        int firstIndex = s.length();
        for(String key : pos.keySet()) {
            System.out.println(key + "|" + pos.get(key).info.size());
            if(pos.get(key).info.size() == 1) {
                int cur = pos.get(key).info.get(0);
                if(firstIndex > cur) {
                    firstIndex = cur;
                }
            }
        }
        if(firstIndex == s.length()) {
            return -1;
        } else {
            return firstIndex;
        }
    }
}
