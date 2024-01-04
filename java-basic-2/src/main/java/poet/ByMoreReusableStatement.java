package poet;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.io.IOException;

public class ByMoreReusableStatement {
    private static MethodSpec computeRangeMethod(String methodName, int from, int to, String op) {
        return MethodSpec.methodBuilder(methodName)
                .returns(int.class)
                .addStatement("int result = 1")
                .beginControlFlow("for(int i=" + from + "; i<" + to + "; i++)")
                .addStatement("result = result " + op + " i")
                .endControlFlow()
                .addStatement("return result")
                .build();
    }

    public static void main(String[] args) throws IOException {
        MethodSpec main = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(String[].class, "args")
                .addStatement("ByMoreReusableStatement obj = new ByMoreReusableStatement()")
                .addStatement("int result = obj.multiply10to20()")
                .addStatement("System.out.println($S + result)", "result: ")
                .build();

        TypeSpec byCode = TypeSpec.classBuilder("ByMoreReusableStatement")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(main)
                .addMethod(computeRangeMethod("multiply10to20", 10, 20, "*"))
                .build();

        JavaFile javaFile = JavaFile.builder("com.example.helloword", byCode).build();

        javaFile.writeTo(System.out);
    }
}
