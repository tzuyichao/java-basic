package org.apache.kafka.connect.json;

import org.apache.kafka.connect.data.SchemaAndValue;
import org.apache.kafka.connect.data.Struct;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

public class ConverterTests {
    @Test
    void test_value() throws URISyntaxException, IOException {
        JsonConverter converter = new JsonConverter();
        converter.configure(
                java.util.Map.of("schemas.enable", true),
                false
        );
        URL url = getClass().getClassLoader().getResource("value_example.json");
        String json = Files.readString(Path.of(url.toURI()), StandardCharsets.UTF_8);
        SchemaAndValue schemaAndValue = converter.toConnectData("test-topic", json.getBytes(java.nio.charset.StandardCharsets.UTF_8));

        System.out.printf("Schema: %s%n", schemaAndValue.schema());
        System.out.printf("Value: %s%n", schemaAndValue.value());

        if(schemaAndValue.value() instanceof Struct) {
            Struct struct = (Struct) schemaAndValue.value();
            if(struct.get("publish_date") instanceof Date) {
                System.out.printf("Field 'publish_date': %s%n", (Date)struct.get("publish_date"));
            }
            if(struct.get("modify_date") instanceof Date) {
                System.out.printf("Field 'modify_date': %s%n", (Date)struct.get("modify_date"));
            }
        }
    }
}
