package javassist.basic;

/**
 * https://www.ibm.com/developerworks/cn/java/j-dyn0916/index.html?ca=drs-
 */
public class StringBuilder {
    public static String buildString(int length) {
        String result = "";
        for (int i = 0; i < length; i++) {
            result += (char)(i%26 + 'a');
        }
        return result;
    }

    public static void main(String[] args) {
        StringBuilder inst = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            String result = inst.buildString(Integer.parseInt(args[i]));
            System.out.println("Constructed string of length " +
                    result.length());
        }
    }
}
