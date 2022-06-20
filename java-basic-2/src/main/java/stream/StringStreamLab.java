package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringStreamLab {
    /**
     * 1268. Search Suggestions System
     * https://leetcode.com/problems/search-suggestions-system/
     * 
     * @param products
     * @param searchWord
     * @return
     */
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> res = new ArrayList();
        Arrays.sort(products);
        StringBuilder c = new StringBuilder();
        for(char ch: searchWord.toCharArray()) {
            c.append(ch);
            res.add(Arrays.stream(products).filter(it -> it.startsWith(c.toString())).limit(3).collect(Collectors.toList()));
        }
        return res;
    }
}
