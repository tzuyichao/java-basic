package brute;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringHalvesAlikeTest {
    private StringHalvesAlike stringHalvesAlike;

    @Before
    public void setup() {
        stringHalvesAlike = new StringHalvesAlike();
    }

    @Test
    public void test_book() {
        assertTrue(stringHalvesAlike.halvesAreAlike("book"));
    }

    @Test
    public void test_textbook() {
        assertFalse(stringHalvesAlike.halvesAreAlike("textbook"));
    }

    @Test
    public void test_MerryChristmas() {
        assertFalse(stringHalvesAlike.halvesAreAlike("MerryChristmas"));
    }

    @Test
    public void test_AbCdEfGh() {
        assertTrue(stringHalvesAlike.halvesAreAlike("AbCdEfGh"));
    }

}
