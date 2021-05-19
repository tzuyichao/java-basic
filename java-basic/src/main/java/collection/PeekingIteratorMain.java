package collection;

import java.util.ArrayList;

public class PeekingIteratorMain {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        PeekingIterator iterator = new PeekingIterator(list.iterator());
        System.out.println(iterator.next());
        System.out.println(iterator.peek());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
    }
}
