package compiler;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.util.List;

public class CompilationTask {
    public static void main(String[] args) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        Iterable<String> options = List.of("-d", "target\\other-classes");
        var sources = fileManager.getJavaFileObjectsFromStrings(List.of("src\\main\\java\\time\\TimeLab.java"));

        JavaCompiler.CompilationTask task = compiler.getTask(
                null,
                fileManager,
                null,
                options,
                null,
                sources
        );
        Boolean result = task.call();
        System.out.println(result);
    }
}
