package basic;

public class ArrayLab {
    public int[] reverse(int[] arr) {
        var l = arr.length;
        var r = new int[l];

        for(int i=0; i<l; i++) {
            r[i] = arr[l-i-1];
        }

        return r;
    }

    public int[] mul(int[] a, int[] b) {
        int[] ra = reverse(a);
        int[] rb = reverse(b);
        var r = new int[a.length+b.length];

        for(int i=0; i<b.length; i++) {
            for(int j=0; j<a.length; j++) {
                var v = rb[i] * ra[j];
                r[i+j] += v;
            }
        }

        int cval = 0;
        for(int i=0; i<r.length; i++) {
            var val = r[i] + cval;
            var q = val / 10;
            var m = val % 10;
            r[i] = m;
            if(q > 0) {
                cval = q;
            } else {
                cval = 0;
            }
        }

        return reverse(r);
    }
}
