package collection;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * = 284
 * Runtime: 4 ms, faster than 97.07% of Java online submissions for Peeking Iterator.
 * Memory Usage: 38.9 MB, less than 68.00% of Java online submissions for Peeking Iterator.
 */
public class PeekingIterator implements Iterator<Integer> {
    private Queue<Integer> store;
    public PeekingIterator(Iterator<Integer> iterator) {
        store = new LinkedList<>();
        while(iterator.hasNext()) {
            store.offer(iterator.next());
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return store.peek();
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        return store.poll();
    }

    @Override
    public boolean hasNext() {
        return !store.isEmpty();
    }
}
