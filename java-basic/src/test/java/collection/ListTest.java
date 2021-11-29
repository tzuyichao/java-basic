package collection;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ListTest {
    @Test
    public void simple_add_null() {
        List<Integer> l = new ArrayList<>();
        l.add(null);
        assertEquals(1, l.size());
    }

    @Test
    public void test_equals_of_list_of() {
        assertEquals(List.of(2, 2), List.of(2, 2));
        assertTrue(List.of(2, 2).equals(List.of(2, 2)));
    }

    @Test
    public void test_set() {
        Set<List<Integer>> set = new HashSet<>();
        set.add(List.of(2, 2));
        set.add(Collections.EMPTY_LIST);
        set.add(List.of(2, 2));
        assertEquals(2, set.size());
    }

    @Test(expected = NullPointerException.class)
    public void test_addAll() {
        List<String> all = new ArrayList<>();
        all.addAll(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_list_of() {
        var l = List.of(1, 2, 3);
        l.add(4);
    }

    @Test
    public void test_quiz2_chapter5_3() {
        List<Integer> number1 = Arrays.asList(1, 2, 3);
        List<Integer> number2 = Arrays.asList(3, 4);

        List<int[]> result = number1.stream()
                .flatMap(it -> {
                    return number2.stream().map(it2 -> new int[] {it, it2});
                }).collect(Collectors.toList());
        assertThat(result)
                .isNotNull()
                .satisfies(r -> assertThat(r.size()).isEqualTo(6));
    }

    @Test
    public void test_quiz3_chapter5_3() {
        List<Integer> number1 = Arrays.asList(1, 2, 3);
        List<Integer> number2 = Arrays.asList(3, 4);
        List<int[]> result = number1.stream()
                .flatMap(it -> {
                    return number2.stream()
                            .filter(it2 -> (it2 + it) % 3 == 0)
                            .map(it2 -> new int[] {it, it2});
                })
                .collect(Collectors.toList());
        assertThat(result)
                .isNotNull()
                .satisfies(r -> assertThat(r.size()).isEqualTo(2));
    }
}
