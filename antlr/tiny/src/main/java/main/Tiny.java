package main;

import listener.TinyErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import tiny.TinyLexer;
import tiny.TinyParser;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * https://github.com/antlr/grammars-v4/tree/master/tiny
 */
public class Tiny {
    public static void main(String[] args) {
        try {
            TinyErrorListener tinyErrorListener = new TinyErrorListener();
            CharStream input = CharStreams.fromPath(Paths.get("src/test/resources/example1.txt"));
            tiny.TinyLexer lexer = new TinyLexer(input);
            lexer.removeErrorListeners();
            lexer.addErrorListener(tinyErrorListener);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            TinyParser parser = new TinyParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(tinyErrorListener);
            ParseTree parseTree = parser.program();

            if(!tinyErrorListener.isSyntaxError()) {
                System.out.println("LISP:");
                System.out.println(parseTree.toStringTree(parser));
                System.out.println();
            } else {
                System.out.println("Syntax Error");
                System.out.println(tinyErrorListener.getSyntaxErrorMessages());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
