package basic;

import java.util.BitSet;

public class BitSetTest {
    public static void main(String[] args) {
        BitSet data = new BitSet();
        System.out.println(data.toString());
        data.set("test".hashCode());
        System.out.println(data.toString());
        System.out.println(data.get("test".hashCode()));
        System.out.println(data.get("test2".hashCode()));
    }
}
