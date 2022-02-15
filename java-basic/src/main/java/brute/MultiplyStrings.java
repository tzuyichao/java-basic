package brute;

/**
 * 43. Multiply Strings
 *
 * Runtime: 1 ms, faster than 100.00% of Java online submissions for Multiply Strings.
 * Memory Usage: 42.4 MB, less than 37.57% of Java online submissions for Multiply Strings.
 */
public class MultiplyStrings {
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

    public int[] toIntArray(String num) {
        // no validation
        var l = num.length();
        var r = new int[l];

        for(int i=0; i<l; i++) {
            char c = num.charAt(i);
            r[i] = c - '0';
        }

        return r;
    }

    public String multiply(String num1, String num2) {
        if("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        int[] n1 = toIntArray(num1);
        int[] n2 = toIntArray(num2);
        int[] r = mul(n1, n2);

        StringBuilder result = new StringBuilder();
        boolean leadingZero = r[0] == 0?true:false;
        for(int i=0; i<r.length; i++) {
            if(leadingZero) {
                if(r[i] != 0) {
                    result.append(r[i]);
                    leadingZero = false;
                }
            } else {
                result.append(r[i]);
            }
        }
        return result.toString();
    }
}
