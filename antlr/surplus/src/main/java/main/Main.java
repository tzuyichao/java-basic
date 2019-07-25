package main;

import listener.SurplusErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import surplus.SurplusEvaluator;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
        try {
            SurplusErrorListener surplusErrorListener = new SurplusErrorListener();
            CharStream input = CharStreams.fromPath(Paths.get("src/test/resources/example2.txt"));
            surplus.SurplusLexer lexer = new surplus.SurplusLexer(input);
            lexer.removeErrorListeners();
            lexer.addErrorListener(surplusErrorListener);

            CommonTokenStream tokens = new CommonTokenStream(lexer);
            surplus.SurplusParser parser = new surplus.SurplusParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(surplusErrorListener);

            ParseTree parseTree = parser.prog();

            if(!surplusErrorListener.isSyntaxError()) {
                ParseTreeWalker walker = new ParseTreeWalker();
                SurplusEvaluator evaluator = new SurplusEvaluator();
                walker.walk(evaluator, parseTree);
                System.out.println(evaluator.getResult());
            } else {
                System.out.println("Syntax Error");
                System.out.println(surplusErrorListener.getSyntaxErrorMessages());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
