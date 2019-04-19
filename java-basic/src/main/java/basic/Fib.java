package basic;

public class Fib {
    public static int fib(int n) {
        if(n == 0) {
            return 0;
        }
        if(n == 1) {
            return 1;
        }
        // n_1 : n-1
        // n_2 : n-2
        int fib = 0, n_2 = 0, n_1 = 1;
        for(int i=2; i<=n; i++) {
            fib = n_2 + n_1;
            n_2 = n_1;
            n_1 = fib;
        }
        return fib;
    }
    public static void main(String[] args) {
        System.out.println(fib(1000000000));
    }
}
