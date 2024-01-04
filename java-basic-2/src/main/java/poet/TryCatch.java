package poet;

import com.squareup.javapoet.MethodSpec;

public class TryCatch {
    public static void main(String[] args) {
        MethodSpec main = MethodSpec.methodBuilder("main")
                .beginControlFlow("try")
                .addStatement("throw new Exception($S)", "Failed")
                .nextControlFlow("catch ($T e)", Exception.class)
                .addStatement("throw new $T(e)", RuntimeException.class)
                .endControlFlow()
                .build();

        System.out.println(main);
    }
}
