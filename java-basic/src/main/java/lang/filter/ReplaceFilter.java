package lang.filter;

import java.util.List;

public class ReplaceFilter implements NameFilter {
    private List<String> keys;

    public ReplaceFilter(List<String> keys) {
        this.keys = keys;
    }

    @Override
    public String filter(String src, String mask) {
        for(String key: keys) {
            src = src.replace(key, mask);
        }
        return src;
    }
}
