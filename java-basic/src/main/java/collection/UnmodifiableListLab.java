package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UnmodifiableListLab {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        List<Integer> list2 = Collections.unmodifiableList(list);
        List<Integer> list3 = List.copyOf(list);
        System.out.println("list: " + list.toString());
        System.out.println("list2: " + list2.toString());
        System.out.println("list3: " + list3.toString());
        list.add(4);
        System.out.println("list: " + list.toString());
        System.out.println("after add 4 to list, list2 goes to: " + list2.toString());
        System.out.println("after add 4 to list, list3 goes to: " + list3.toString());
    }
}
