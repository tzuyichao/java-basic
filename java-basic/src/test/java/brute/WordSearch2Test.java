package brute;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WordSearch2Test {
    @Test
    public void test_happy_1() {
        WordSearch2 solver = new WordSearch2();
        assertThat(solver.findWords(new char[][] {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}},
                new String[] {
                        "oath", "pea", "eat", "rain"
                }))
                .isEqualTo(List.of("oath", "eat"));
    }

    @Test
    public void test_happy_2() {
        WordSearch2 solver = new WordSearch2();
        assertThat(solver.findWords(new char[][] {
                        {'a', 'b'},
                        {'c', 'd'}},
                new String[] {
                        "abcd"
                }))
                .isEqualTo(Collections.EMPTY_LIST);
    }
}
