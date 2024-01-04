package poet;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.io.IOException;

public class ByStatement {
    private static MethodSpec createMainByStatement() {
        return MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(String[].class, "args")
                .addStatement("int total = 0")
                .beginControlFlow("for(int=0; i<10; i++)")
                .addStatement("total += i")
                .endControlFlow()
                .addStatement("System.out.println($S + total)", "Total:")
                .build();
    }

    public static void main(String[] args) throws IOException {
        MethodSpec main = createMainByStatement();
        TypeSpec byCode = TypeSpec.classBuilder("ByCode")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(main)
                .build();

        JavaFile javaFile = JavaFile.builder("com.example.helloword", byCode).build();

        javaFile.writeTo(System.out);
    }
}
