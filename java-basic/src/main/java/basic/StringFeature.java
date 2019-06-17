package basic;

public class StringFeature {
    public static void main(String[] args) {
        String str1 = "abc";
        // repeat() java11 add
        System.out.println(str1.repeat(3));
        // java 12
        String str2 = "abc\nabc\nabc";
        System.out.println(str2.indent(3));
        String str3 = "hello";
        String str3_transform = str3.transform(input -> input + ", world!");
        System.out.println(str3_transform);
    }
}
