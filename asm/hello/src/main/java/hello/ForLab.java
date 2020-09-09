package hello;

public class ForLab {
    public int run(int n) {
        int result = 0;
        for(int i=0; i<=n; i++) {
            result += i;
        }
        return result;
    }

    public boolean if_example(int i) {
        if(i < 0) {
            return true;
        }
        return false;
    }

    /**
     public void arr();
     Code:
     0: iconst_4
     1: newarray       int
     3: dup
     4: iconst_0
     5: bipush        10
     7: iastore
     8: dup
     9: iconst_1
     10: bipush        20
     12: iastore
     13: dup
     14: iconst_2
     15: bipush        30
     17: iastore
     18: dup
     19: iconst_3
     20: bipush        40
     22: iastore
     23: astore_1
     24: return
     */
    public void arr() {
        int[] arr = {10, 20, 30, 40};
    }
}
