package text;

import org.junit.Test;

import java.util.MissingFormatArgumentException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StringLabTest {
    @Test(expected = IllegalArgumentException.class)
    public void test_makeString_with_null_template() {
        StringLab.makeString(null, 1, 2, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_makeString_with_null_items() {
        StringLab.makeString(StringLab.FORMAT, null);
    }

    @Test
    public void test_makeString_happy() {
        String result = StringLab.makeString(StringLab.FORMAT, "hasCreatedCommunity", Boolean.FALSE);
        assertNotNull(result);
        assertEquals("hasCreatedCommunity=false", result);
    }

    @Test(expected = MissingFormatArgumentException.class)
    public void test_makeString_insufficient_item() {
        StringLab.makeString(StringLab.FORMAT, "hasCreatedCommunity");
    }
}
