package brute;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WordSearchTest {
    @Test
    public void test_happy_1() {
        WordSearch solver = new WordSearch();
        assertThat(solver.exist(new char[][] {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'R', 'E'}}, "ABCB"))
                .isFalse();
    }

    @Test
    public void test_happy_2() {
        WordSearch solver = new WordSearch();
        assertThat(solver.exist(new char[][] {{'A'}}, "A"))
                .isTrue();
    }

    @Test
    public void test_happy_3() {
        WordSearch solver = new WordSearch();
        assertThat(solver.exist(new char[][] {{'A'}}, "B"))
                .isFalse();
    }

    @Test
    public void test_happy_4() {
        WordSearch solver = new WordSearch();
        assertThat(solver.exist(new char[][] {{'A'}}, "AB"))
                .isFalse();
    }

    @Test
    public void test_happy_5() {
        WordSearch solver = new WordSearch();
        assertThat(solver.exist(new char[][] {{'A', 'A'}}, "A"))
                .isTrue();
    }

    @Test
    public void test_happy_6() {
        WordSearch solver = new WordSearch();
        assertThat(solver.exist(new char[][] {{'A', 'A'}}, "AAA"))
                .isFalse();
    }

    /**
     * [["A","B","C","E"],["S","F","E","S"],["A","D","E","E"]]
     * "ABCESEEEFS"
     */
    @Test
    public void test_happy_7() {
        WordSearch solver = new WordSearch();
        assertThat(solver.exist(new char[][] {{'A', 'B', 'C', 'E'}, {'S', 'F', 'E', 'S'}, {'A', 'D', 'E', 'E'}}, "ABCESEEEFS"))
                .isTrue();
    }
}
