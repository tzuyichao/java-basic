package lambda;

import java.util.ArrayList;
import java.util.List;

public class FunctionalInterfacesMain {
    public static List<Melon> filter(List<Melon> melons, MelonPredicate predicate) {
        List<Melon> result = new ArrayList<>();

        for(Melon melon: melons) {
            if(melon != null&& predicate.test(melon)) {
                result.add(melon);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<Melon> melons = List.of(
                new Melon("watermelon", 10, "Taiwan"),
                new Melon("Cantaloupe", 4, "Taiwan"),
                new Melon("Honeydew", 3, "Taiwan"));
        List<Melon> filerByType = filter(melons, melon -> melon.getType().equalsIgnoreCase("watermelon"));
        System.out.println(filerByType);
    }
}
