package kyu;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayingWithDigitsTest {
    @Test
    public void test() {
        PlayingWithDigits playingWithDigits = new PlayingWithDigits();
        int r = playingWithDigits.digPow(89, 1);
        assertEquals(1, r);
        r = playingWithDigits.digPow(92, 1);
        assertEquals(-1, r);
        r = playingWithDigits.digPow(46288, 3);
        assertEquals(51, r);
        r = playingWithDigits.digPow(695, 2);
        assertEquals(2, r);
    }
}
