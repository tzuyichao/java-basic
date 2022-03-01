package brute;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

public class LRUCacheTest {
    @Test
    public void happy1() {
        var cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);
        cache.put(3, 3);
        assertThat(cache.get(2), is(equalTo(-1)));
        cache.put(4, 4);
        assertThat(cache.get(1), is(equalTo(-1)));
        assertThat(cache.get(3), is(equalTo(3)));
        assertThat(cache.get(4), is(equalTo(4)));
    }

    @Test
    public void happy2() {
        var cache = new LRUCache(2);
        assertThat(cache.get(2), is(equalTo(-1)));
        cache.put(2, 6);
        assertThat(cache.get(1), is(equalTo(-1)));
        cache.put(1, 5);
        cache.put(1, 2);
        assertThat(cache.get(1), is(equalTo(2)));
        assertThat(cache.get(2), is(equalTo(6)));
    }

    @Test
    public void happy3() {
        var cache = new LRUCache(2);
        cache.put(2, 1);
        cache.put(3, 2);
        assertThat(cache.get(3), is(equalTo(2)));
        assertThat(cache.get(2), is(equalTo(1)));
        cache.put(4, 3);
        assertThat(cache.get(2), is(equalTo(1)));
        assertThat(cache.get(3), is(equalTo(-1)));
        assertThat(cache.get(4), is(equalTo(3)));
    }
}
