package collection;

import java.util.Arrays;
import java.util.LinkedList;

public class ListLab {
    public static void main(String[] args) {
        try {
            var list = new LinkedList<Integer>();

            list.add(100, 100);
            list.add(1, 1);

            System.out.println(list);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        String topics1 = "test.ttt.topic";
        System.out.println(Arrays.stream(topics1.split(";")).distinct().toList());
        String topics2 = "test1.ttt.topic;test2.ttt.topic;test1.ttt.topic";
        System.out.println(Arrays.stream(topics2.split(";")).distinct().map(str -> str+"q").toList());
    }
}
