import calc.CalcLexer;
import calc.CalcParser;
import calc.EvalVisitor;
import calc.Evaluator;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.nio.file.Paths;

/**
 * from https://abcdabcd987.com/notes-on-antlr4/
 */
public class Calc {
    public static void main(String[] args) throws Exception {
        CharStream input = CharStreams.fromPath(Paths.get("src/test/resources/1.txt"));
        CalcLexer lexer = new CalcLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CalcParser parser = new CalcParser(tokens);
        ParseTree tree = parser.calc(); // calc is the starting rule

        System.out.println("LISP:");
        System.out.println(tree.toStringTree(parser));
        System.out.println();

        System.out.println("Visitor:");
        EvalVisitor evalByVisitor = new EvalVisitor();
        evalByVisitor.visit(tree);
        System.out.println();

        System.out.println("Listener:");
        ParseTreeWalker walker = new ParseTreeWalker();
        Evaluator evalByListener = new Evaluator();
        walker.walk(evalByListener, tree);
    }
}
