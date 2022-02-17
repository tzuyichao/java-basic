package challenge;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UniqueCharactersTest {
    @Test
    public void test_happy_1() {
        UniqueCharacters solver = new UniqueCharacters();
        assertThat(solver.test("a b c d"))
                .isTrue();
    }

    @Test
    public void test_happy_2() {
        UniqueCharacters solver = new UniqueCharacters();
        assertThat(solver.test("a測試c d"))
                .isTrue();
    }

    @Test
    public void test_happy_3() {
        UniqueCharacters solver = new UniqueCharacters();
        assertThat(solver.test("a測試c d測"))
                .isFalse();
    }
}
