package org.apache.kafka.connect.data;

import org.junit.jupiter.api.Test;

import org.apache.kafka.connect.data.SchemaBuilder;

public class SchemaBuilderTests {
    private String schemaToString(Schema schema) {
        String template = "Schema{name: %s, version: %s}%n\tFields:%s";
        StringBuilder fields = new StringBuilder();
        for (Field field : schema.fields()) {
            fields.append(String.format("%n\t\t%s", field.toString()));
        }
        return String.format(template, schema.name(), schema.version(), fields.toString());
    }

    @Test
    void test_struct_method() {
        Schema schema = SchemaBuilder.struct()
                .name("com.example.User")
                .version(1)
                .field("name", Schema.STRING_SCHEMA)
                .field("age", Schema.INT32_SCHEMA)
                .field("price", Decimal.builder(2).build())
                .build();
        System.out.printf("%s%n", schemaToString(schema));
    }
}
