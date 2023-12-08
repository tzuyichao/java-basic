package classloader;

public class ExampleNoClassDef {
    public static class BadInt {
        private static int thisIsFine = 1/0;
    }

    public static void main(String[] args) {
        try {
            var init = new BadInt();
        } catch(Throwable t) {
            System.out.println(t);
        }
        var init2 = new BadInt();
        System.out.println(init2.thisIsFine);
    }
}
