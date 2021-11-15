package collection;

public class FenwickTree {
    private final int n;
    private long[] tree;

    public FenwickTree(long[] values) {
        values[0] = 0L;
        this.n = values.length;
        tree = values.clone();
        for(int i=1; i<n; i++) {
            int parent = i + lsb(i);
            System.out.println("i=" + i + ", parent=" + parent);
            if(parent < n) {
                tree[parent] += tree[i];
            }
        }
    }

    private static int lsb(int i) {
        return i & -i;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i=0; i<n; i++) {
            sb.append(tree[i]);
            if(i != n-1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
