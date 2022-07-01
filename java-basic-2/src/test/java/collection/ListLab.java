package collection;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListLab {
    @Test
    void test_insert() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(0, 100);
        list.add(0, 10);
        list.add(1, 7);
        list.add(1, 6);
        System.out.println(list);
    }
}
