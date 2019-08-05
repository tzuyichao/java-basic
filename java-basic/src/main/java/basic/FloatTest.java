package basic;

public class FloatTest {
    public static void main(String[] args) {
        float val1 = 2/+0.0f;
        float val2 = 2/-0.0f;
        if(val1 == Float.POSITIVE_INFINITY) {
            System.out.println("Equal!!");
        }
        if(val2 == Float.NEGATIVE_INFINITY) {
            System.out.println("Equal!!");
        }
    }
}
