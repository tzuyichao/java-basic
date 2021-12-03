package collection;

import java.util.List;
import java.util.function.Predicate;

public class QuizTakeWhile {
    public static <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
        int idx = 0;
        for(A item: list) {
            if(!p.test(item)) {
                return list.subList(0, idx);
            }
            idx++;
        }
        return list;
    }
}
