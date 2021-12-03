package collection;

import org.junit.Test;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class QuizTakeWhileTest {
    @Test
    public void test_happy() {
        List<Integer> lists = List.of(1, 2, 3, 4);
        Predicate<Integer> p = (it) -> it % 2 == 0;
        List<Integer> expect = lists.stream().takeWhile(p).collect(Collectors.toList());
        List<Integer> result = QuizTakeWhile.takeWhile(lists, p);
        assertThat(result)
                .isNotNull()
                .satisfies(r -> assertThat(r.size()).isEqualTo(expect.size()));
    }

    @Test
    public void test_happy2() {
        List<Integer> lists = List.of(2, 2, 3, 4);
        Predicate<Integer> p = (it) -> it % 2 == 0;
        List<Integer> expect = lists.stream().takeWhile(p).collect(Collectors.toList());
        List<Integer> result = QuizTakeWhile.takeWhile(lists, p);
        assertThat(result)
                .isNotNull()
                .satisfies(r -> assertThat(r.size()).isEqualTo(expect.size()));
    }
}
