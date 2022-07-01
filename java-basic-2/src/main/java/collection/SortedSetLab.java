package collection;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.SortedSet;
import java.util.TreeSet;

public class SortedSetLab {
    public static void main(String[] args) {
        SortedSet<Integer> sortedSet = new TreeSet<>(Comparator.reverseOrder());
        sortedSet.add(3);
        sortedSet.add(5);
        sortedSet.add(9);
        System.out.println(sortedSet);

        PriorityQueue<Integer> q = new PriorityQueue<>(sortedSet);
        System.out.println(q);
        q.offer(17);
        System.out.println(q);
        System.out.println(5 >>> 1);
        System.out.println(5 >> 1);
        System.out.println(-5 >>> 1);
        System.out.println(-5 >> 1);
    }
}
