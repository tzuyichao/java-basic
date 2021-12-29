package stream;

import collection.sort.Melon;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMapLab {
    Melon[][] melonsArray = {
            {new Melon("Gac", 2000), new Melon("Hemi", 1600)},
            {new Melon("Gac", 2000), new Melon("Apollo", 2000)},
            {new Melon("Horned", 1700), new Melon("Hemi", 1600)}
    };

    @Test
    public void error() {
        Stream<Melon[]> streamOfMelonArray = Arrays.stream(melonsArray);
        System.out.println(streamOfMelonArray.distinct().collect(Collectors.toList()));
    }

    @Test
    public void error2() {
        Stream<Melon[]> streamOfMelonArray = Arrays.stream(melonsArray);
        System.out.println(streamOfMelonArray.map(Arrays::stream).distinct().collect(Collectors.toList()));
    }

    @Test
    public void success() {
        Stream<Melon[]> streamOfMelonArray = Arrays.stream(melonsArray);
        System.out.println(streamOfMelonArray.flatMap(Arrays::stream).distinct().collect(Collectors.toList()));
    }
}
