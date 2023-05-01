package basic;

import java.util.ArrayList;
import java.util.Collection;

public class LVTILab {
    public static void main(String[] args) {
        /*
        Collection<?> stuff = new ArrayList<>();
        stuff.add("hello");     // compile error
        stuff.add("world");     // compile error
         */
        var stuff = new ArrayList<>();
        stuff.add("hello");
        stuff.add("world");
        System.out.println(stuff);
    }
}
