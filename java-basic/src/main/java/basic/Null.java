package basic;

public class Null {
    public static void greet() {
        System.out.println("Hello world!");
    }

    public static void main(String[] args) {
        ((Null) null).greet();
        for(int i=0; i<10; i++) {
            Null obj = new Null();
        }
    }
}
