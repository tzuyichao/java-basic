package brute;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LengthOfLastWordTest {
    LengthOfLastWord lengthOfLastWord;

    @Before
    public void init() {
        lengthOfLastWord = new LengthOfLastWord();
    }

    @Test
    public void test_hello_world(){
        String s = "hello world";
        assertEquals(5, lengthOfLastWord.lengthOfLastWord(s));
    }

    @Test
    public void test_space(){
        String s = " ";
        assertEquals(0, lengthOfLastWord.lengthOfLastWord(s));
    }

    @Test
    public void test_multiple_space(){
        String s = "    ";
        assertEquals(0, lengthOfLastWord.lengthOfLastWord(s));
    }

    @Test
    public void test_wrong_2(){
        String s = "b a ";
        assertEquals(1, lengthOfLastWord.lengthOfLastWord(s));
    }
}
