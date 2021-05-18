package collection;

import java.util.TreeSet;

public class TreeSetLab1 {
    public static void main(String[] args) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(1);
        int i = 1;
        while(i < 20) {
            int temp = treeSet.pollFirst();
            treeSet.add(temp*2);
            treeSet.add(temp*3);
            treeSet.add(temp*5);
            System.out.println(treeSet);
            i++;
        }
        System.out.println(treeSet.pollFirst());
    }
}
