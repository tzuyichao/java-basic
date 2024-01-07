package poet;

import com.squareup.javapoet.MethodSpec;

public class LiteralLab {


    public static void main(String[] args) {
        MethodSpec byCode = MethodSpec.methodBuilder("multiply10to20")
                .returns(int.class)
                .addStatement("int result = 1")
                .beginControlFlow("for (int i=$L; i < $L; i++)", 10, 20)
                .addStatement("result = result $L i", "*")
                .endControlFlow()
                .addStatement("return result")
                .build();
        System.out.println(byCode );
    }
}
