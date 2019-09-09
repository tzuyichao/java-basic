package util;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

/**
 * Effective Java 3rd Edition: Item 36 and ...
 */
public class EnumSetLab1 {
    public static void main(String[] args) {
        final EnumSet<Text.Style> BOLD_AND_ITALIC = EnumSet.of(Text.Style.BOLD, Text.Style.ITALIC);
        Text text1 = new Text();
        text1.applyStyles(EnumSet.of(Text.Style.STRIKE_THROUGH, Text.Style.BOLD));
        System.out.println(text1.toString());

        Set<Text.Style> complementOfBoldAndItalic = EnumSet.complementOf(BOLD_AND_ITALIC);
        System.out.println("complement: " + complementOfBoldAndItalic);

        Set<Text.Style> allStyles = EnumSet.allOf(Text.Style.class);
        System.out.println("all Styles: " + allStyles);

        Set<Text.Style> noneOf = EnumSet.noneOf(Text.Style.class);
        System.out.println("none of: " + noneOf);

        Set<Text.Style> copy = EnumSet.copyOf(BOLD_AND_ITALIC);
        System.out.println(copy.equals(BOLD_AND_ITALIC));
    }
}

class Text {
    public enum Style {BOLD, ITALIC, UNDERLINE, STRIKE_THROUGH};
    private Set<Style> styles = new HashSet<>();

    public void applyStyles(Set<Style> styles) {
        this.styles.addAll(styles);
    }

    @Override
    public String toString() {
        return "Text{" +
                "styles=" + styles +
                '}';
    }
}
