package collection.sort;

import java.util.Arrays;
import java.util.Comparator;

public class MelonSortMain {
    public static void main(String[] args) {
        Melon[] melons = new Melon[] {
                new Melon("cantaloupe", 5),
                new Melon("watermelon", 11),
                new Melon("hami", 2),
                new Melon("snap melon", 6)
        };
        System.out.println(Arrays.toString(melons));
        Arrays.sort(melons, new Comparator<Melon>() {
            @Override
            public int compare(Melon m1, Melon m2) {
                return Integer.compare(m1.getWeight(), m2.getWeight());
            }
        });
        System.out.println(Arrays.toString(melons));
        Arrays.sort(melons, (m1, m2) -> {
            return Integer.compare(m1.getWeight(), m2.getWeight());
        });
        Arrays.sort(melons, Comparator.comparingInt(Melon::getWeight).reversed());
        System.out.println(Arrays.toString(melons));
    }
}
