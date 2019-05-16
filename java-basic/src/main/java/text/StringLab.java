package text;

import basic.Asserts;

public class StringLab {
    public static final String SEPARATOR = "=";
    public static final String FORMAT = "%s" + SEPARATOR + "%s";

    public static String makeString(String template, Object... items) {
        Asserts.notNull(template, "template should not be null");
        Asserts.notNull(items, "items should not be null");
        return String.format(template, items);
    }

    public static void main(String[] args) {
        System.out.println(StringLab.makeString(FORMAT, "hasCreatedCommunity", Boolean.FALSE));
    }
}
