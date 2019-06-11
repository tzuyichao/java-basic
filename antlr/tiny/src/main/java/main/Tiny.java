package main;

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
            CharStream input = CharStreams.fromPath(Paths.get("src/test/resources/example1.txt"));
            tiny.TinyLexer lexer = new TinyLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            TinyParser parser = new TinyParser(tokens);
            ParseTree parseTree = parser.program();

            System.out.println("LISP:");
            System.out.println(parseTree.toStringTree(parser));
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
