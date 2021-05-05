package brute;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 1002
 * Runtime: 32 ms, faster than 5.24% of Java online submissions for Find Common Characters.
 * Memory Usage: 39.7 MB, less than 9.30% of Java online submissions for Find Common Characters.
 */
public class FindCommonCharacters {
    public Map<String, Integer> stat(String str) {
        Map<String, Integer> res = new HashMap<>();

        Arrays.stream(str.split("")).forEach(elem -> {
            if(res.containsKey(elem)) {
                res.put(elem, res.get(elem)+1);
            } else {
                res.put(elem, 1);
            }
        });

        return res;
    }

    public List<String> commonChars(String[] A) {
        if(A.length == 1) {
            List<String> res = Arrays.stream(A[0].split("")).collect(Collectors.toList());
            return res;
        } else {
            Map<String, Integer> stat = stat(A[0]);
            List<String> removeKey = new ArrayList<>();
            for(int i=1; i<A.length; i++) {
                Map<String, Integer> sub = stat(A[i]);
                sub.entrySet().stream().forEach(entry -> {
                    if(stat.containsKey(entry.getKey())) {
                        if(stat.get(entry.getKey()) > entry.getValue()) {
                            stat.put(entry.getKey(), entry.getValue());
                        }
                    }
                });
                for(String key : stat.keySet()) {
                    if(!sub.containsKey(key)) {
                        removeKey.add(key);
                    }
                }
            }
            List<String> res = new ArrayList<>();

            stat.entrySet().stream().forEach(entry -> {
                if(!removeKey.contains(entry.getKey())) {
                    if (entry.getValue() == 1) {
                        res.add(entry.getKey());
                    } else {
                        for (int i = 0; i < entry.getValue(); i++) {
                            res.add(entry.getKey());
                        }
                    }
                }
            });

            return res;
        }
    }
}

