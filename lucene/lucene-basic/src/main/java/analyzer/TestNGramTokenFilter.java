package analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.ngram.NGramTokenFilter;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;

public class TestNGramTokenFilter {
    public static void main(String[] args) throws IOException {
        Analyzer ikAnalyzer = new IKAnalyzer(true);
        TokenStream tokenStream = ikAnalyzer.tokenStream("content", "飯後請先等個半小時再刷牙");

        tokenStream.addAttribute(CharTermAttribute.class);
        tokenStream.reset();

        while(tokenStream.incrementToken()) {
            CharTermAttribute charTermAttribute = tokenStream.getAttribute(CharTermAttribute.class);
            System.out.println(charTermAttribute.toString());
        }
        tokenStream.end();
        tokenStream.close();

        System.out.println("==== With NGramTokenFilter");
        tokenStream = ikAnalyzer.tokenStream("content", "飯後請先等個半小時再刷牙");

        NGramTokenFilter nGramTokenFilter = new NGramTokenFilter(tokenStream, 2, 6, false);

        nGramTokenFilter.addAttribute(CharTermAttribute.class);
        nGramTokenFilter.reset();

        while(nGramTokenFilter.incrementToken()) {
            CharTermAttribute charTermAttribute = nGramTokenFilter.getAttribute(CharTermAttribute.class);
            System.out.println(charTermAttribute.toString());
        }
        tokenStream.end();
        nGramTokenFilter.end();
        tokenStream.close();
        nGramTokenFilter.close();

    }
}
