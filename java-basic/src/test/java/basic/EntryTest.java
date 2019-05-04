package basic;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class EntryTest {

    @Test
    public void generic_constructor_test() {
        Product product = new Product("Apple", BigDecimal.valueOf(2.3));
        product.setSales(100);

        Entry entry = new Entry(product);
        assertEquals(product.toString(), entry.getData());
        assertEquals(100, entry.getRank());
    }
}
