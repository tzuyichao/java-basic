package create;

import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.RootAllocator;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.VarCharVector;
import org.apache.arrow.vector.dictionary.Dictionary;
import org.apache.arrow.vector.dictionary.DictionaryEncoder;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.DictionaryEncoding;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

public class DictionaryEncodingTests {
    @Test
    void test_dictionary_encoding_happy() {
        try (
            BufferAllocator allocator = new RootAllocator();
            VarCharVector countries = new VarCharVector("country-dict", allocator);
            VarCharVector appUserCountriesUnencoded = new VarCharVector("app-user-country-dict", allocator);
        ) {
            countries.allocateNew(10);
            countries.set(0, "Andorra".getBytes(StandardCharsets.UTF_8));
            countries.set(1, "Cuba".getBytes(StandardCharsets.UTF_8));
            countries.set(2, "Grecia".getBytes(StandardCharsets.UTF_8));
            countries.set(3, "Guinea".getBytes(StandardCharsets.UTF_8));
            countries.set(4, "Islandia".getBytes(StandardCharsets.UTF_8));
            countries.set(5, "Malta".getBytes(StandardCharsets.UTF_8));
            countries.set(6, "Tailandia".getBytes(StandardCharsets.UTF_8));
            countries.set(7, "Uganda".getBytes(StandardCharsets.UTF_8));
            countries.set(8, "Yemen".getBytes(StandardCharsets.UTF_8));
            countries.set(9, "Zambia".getBytes(StandardCharsets.UTF_8));
            countries.setValueCount(10);

            Dictionary countriesDictionary = new Dictionary(countries,
                    new DictionaryEncoding(1L, false, new ArrowType.Int(8, true)));
            System.out.println("Dictionary: " + countriesDictionary);

            appUserCountriesUnencoded.allocateNew(5);
            appUserCountriesUnencoded.set(0, "Andorra".getBytes(StandardCharsets.UTF_8));
            appUserCountriesUnencoded.set(1, "Guinea".getBytes(StandardCharsets.UTF_8));
            appUserCountriesUnencoded.set(2, "Islandia".getBytes(StandardCharsets.UTF_8));
            appUserCountriesUnencoded.set(3, "Malta".getBytes(StandardCharsets.UTF_8));
            appUserCountriesUnencoded.set(4, "Uganda".getBytes(StandardCharsets.UTF_8));
            appUserCountriesUnencoded.setValueCount(5);
            System.out.println("Unencoded Data: " + appUserCountriesUnencoded);

            try (FieldVector appUserCountriesDictionaryEncoded = (FieldVector) DictionaryEncoder.encode(appUserCountriesUnencoded, countriesDictionary)) {
                System.out.println("Dictionary-encoded data:" + appUserCountriesDictionaryEncoded);
            }
        }
    }
}
