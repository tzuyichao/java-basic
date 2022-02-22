package ccsp.chapter2;

import java.util.List;

public class GenericSearch {
    public static <T extends Comparable<T>> boolean linearContains(List<T> list, T key) {
        return false;
    }
}
