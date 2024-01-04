package poet;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.io.IOException;

public class ByCode {
    private static MethodSpec createMainByCode() {
        return MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(String[].class, "args")
                .addCode("""
                        int total = 0;
                        for (int i=0; i<10; i++) {
                          total += i;
                        }
                        System.out.println("Total: " + total);
                        """)
                .build();
    }


    public static void main(String[] args) throws IOException {
        MethodSpec main = createMainByCode();
        TypeSpec byCode = TypeSpec.classBuilder("ByCode")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(main)
                .build();

        JavaFile javaFile = JavaFile.builder("com.example.helloword", byCode).build();

        javaFile.writeTo(System.out);
    }
}
