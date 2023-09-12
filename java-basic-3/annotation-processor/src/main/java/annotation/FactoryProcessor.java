package annotation;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Set;

@SupportedAnnotationTypes("annotation.Factory")
@SupportedSourceVersion(SourceVersion.RELEASE_11)
public class FactoryProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Running...");
        System.out.println("Running...:" + annotations);
        for(TypeElement annotation : annotations) {
            roundEnv.getElementsAnnotatedWith(annotation).forEach(element -> {
                String className = element.getSimpleName().toString();
                String packageName = processingEnv.getElementUtils().getPackageOf(element).getQualifiedName().toString();
                try {
                    JavaFileObject javaFileObject = processingEnv.getFiler().createSourceFile(className + "Factory");
                    try(PrintWriter out = new PrintWriter(javaFileObject.openWriter())) {
                        out.write("package " + packageName + ";\n\n");
                        out.write("public class " + className + "Factory {\n");
                        out.write("    public static " + className + " create() {\n");
                        out.write("        return new " + className + "();\n");
                        out.write("    }\n");
                        out.write("}\n");
                    }
                    System.out.println(javaFileObject.toUri().toString());
                } catch (IOException e) {
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, e.getMessage());
                }
            });
        }
        return true;
    }
}
