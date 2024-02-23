package compiler;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * from Core Java 2
 */
public class InvokeCompiler {
    public static void main(String[] args) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int result = compiler.run(null, System.out, System.err, "-d", "target\\other-classes", "-sourcepath", "src\\main\\java", "src\\main\\java\\time\\TimeLab.java");
        System.out.println(STR."Compile Result: \{result}");
    }
}
