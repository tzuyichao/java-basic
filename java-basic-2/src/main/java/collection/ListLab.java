package collection;

import java.util.LinkedList;

public class ListLab {
    public static void main(String[] args) {
        var list = new LinkedList<Integer>();

        list.add(100, 100);
        list.add(1, 1);

        System.out.println(list);
    }
}
