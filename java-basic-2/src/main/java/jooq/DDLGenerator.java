package jooq;

import org.jooq.codegen.GenerationTool;

public class DDLGenerator {
    public static void main(String[] args) {
        try {
            GenerationTool.main(new String[] { "jooq-ddl-generator.xml" });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
